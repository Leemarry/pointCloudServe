package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.config.FileContentHolder;
import com.bear.reseeding.entity.*;
import com.bear.reseeding.model.CurrentUser;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfMediaPhotoService;
import com.bear.reseeding.service.EfMediaService;
import com.bear.reseeding.task.MinioService;
import com.bear.reseeding.utils.ExifUtil;
import com.bear.reseeding.utils.LogUtil;
import com.bear.reseeding.utils.RedisUtils;
import com.bear.reseeding.utils.SubstringUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("media")
public class MediaController {

    @Resource
    private EfMediaPhotoService efMediaPhotoService;

    @Resource
    private EfMediaService efMediaService;
    /**
     * minio
     */
    @Resource
    private MinioService minioService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${minio.EndpointExt}")
    private String EndpointExt;
    @Value("${minio.Endpoint}")
    private String Endpoint;

    /**
     * redis 服务
     */
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private FileContentHolder fileContentHolder;
    //TODO:

    @ResponseBody
    @PostMapping(value = "/{mediaType}/querylist")
    public Result getMediaList(@PathVariable("mediaType") String mediaTypeStr,
                               @RequestParam(value = "startTime", required = false) Date startTime,
                               @RequestParam(value = "endTime", required = false) Date endTime,
                               @RequestParam(value = "mark", required = false) String mark) {
        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
            mark = null;
        }
        try {
            // 15s 模仿网络延迟
//            Thread.sleep(15000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeStr = sdf.format(startTime);
            String endTimeStr = sdf.format(endTime);
            EfMediaType mediaType = EfMediaType.valueOf(mediaTypeStr.toUpperCase());
            // 直接调用枚举中的方法获取媒体列表
            Object mediaList = mediaType.getMediaList(efMediaService, startTimeStr, endTimeStr, mark);
            return ResultUtil.success("success", mediaList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取媒体列表失败！");
        }
    }
    // 更新点云数据

    @ResponseBody
    @PostMapping(value = "/updatePointCloud")
    public Result updatePointCloud(@RequestBody EfPointCloud pointCloud){
        try {
            pointCloud =  efMediaService.insertOrUpdatePointCloud(pointCloud);
            return ResultUtil.success("success" ,pointCloud);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("更新点云数据失败！");
        }
    }


    /**
     *
     * @param uploadTypeStr 媒体类型
     * @param user 当前登录用户
     * @param file 上传的文件
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{uploadType}/upload")
    public Result getuploadList(@PathVariable("uploadType") String uploadTypeStr, @CurrentUser EfUser user, @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime,  @RequestParam(value = "folder", required = false) String folder, HttpServletRequest request ){
        try {
            Integer ucId = user.getId();
            EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
            String bucketName = mediaType.toString();
            // 使用线程上传文件到minio
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //文件大小
            long fileSize = file.getSize();
            if(folder == null || folder.isEmpty() || folder.equals("undefined") || folder.equals("null")){
                folder = "default";
            }
//            //folder = 202408160833_001_B001 截取获取 最后 B001 作为文件夹名
            String[] folderArr = folder.split("_");
            String folderName = "";
            if (folderArr.length > 0) {
                folderName = folderArr[folderArr.length - 1];
            }
            // 文件流
            String url = applicationName + "/" + ucId + "/" + folder+"/" + fileName;
//            System.out.println(folderName+"  文件夹名"+folder);
//            System.out.println(url+"  文件路径");
            InputStream inputStream = file.getInputStream();
//            File fileNew = FileUtil.getThumbnailInputStream(file , 800, 600);
            if (!minioService.putObject(bucketName, url, inputStream,"kmz")) {
                return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
            }
            url = minioService.getPresignedObjectUrl(bucketName, url);
            if ("".equals(url)) {
                return ResultUtil.error("保存文件失败(错误码 4)！");
            }
            // 直接调用枚举中的方法获取媒体列表
            Object uploadMediaList = mediaType.uploadMediaList(efMediaService,file,  user, 1,createTime,url);

            return ResultUtil.success("success",uploadMediaList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }
    }


    @ResponseBody
    @PostMapping(value = "/{uploadType}/upload8")
    public Result getuploadList8(@PathVariable("uploadType") String uploadTypeStr, @CurrentUser EfUser user, @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime, @RequestParam(value = "folder", required = false) String folder, HttpServletRequest request ){
        try {
            Integer ucId = user.getId();
            EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
            String bucketName = mediaType.toString();
            // 使用线程上传文件到minio
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //文件大小
            long fileSize = file.getSize();
            if(folder == null || folder.isEmpty() || folder.equals("undefined") || folder.equals("null")){
                folder = "default";
            }
            //folder = 202408160833_001_B001 截取获取 最后 B001 作为文件夹名
            String[] folderArr = folder.split("_");
            String folderName = "";
            if (folderArr.length > 0) {
                folderName = folderArr[folderArr.length - 1];
            }


            // 文件流
            String url = applicationName + "/" + ucId + "/" + folder+"/" + fileName;
            InputStream inputStream = file.getInputStream();
//            File fileNew = FileUtil.getThumbnailInputStream(file , 800, 600);
            if (!minioService.putObject(bucketName, url, inputStream,"kmz")) {
                return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
            }
            url = minioService.getPresignedObjectUrl(bucketName, url);
            if ("".equals(url)) {
                return ResultUtil.error("保存文件失败(错误码 4)！");
            }
            // 直接调用枚举中的方法获取媒体列表
//            Object uploadMediaList = mediaType.uploadMediaList(efMediaService,file,  user, 1,createTime,url);
            efMediaService.uploadPhotoFile(file,  user, 1,createTime,url);
            return ResultUtil.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }
    }



    /**
     *
     * @param uploadTypeStr 媒体类型 survey- ExifUtil
     * @param user 当前登录用户
     * @param file 上传的文件
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{uploadType}/surveyupload")
    public Result getSurveyuploadList(@PathVariable("uploadType") String uploadTypeStr, @CurrentUser EfUser user, @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime,  @RequestParam(value = "folder", required = false) String folder, HttpServletRequest request ){
        try {
            // 判断 file 是否 是图片类型
            if (!file.getContentType().startsWith("image")) {
                return ResultUtil.error("上传文件不是图片类型！");
            }
            Integer ucId = user.getId();
            EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
            String bucketName = mediaType.toString();
            // 获取文件名
            String fileName = file.getOriginalFilename();

            if(folder == null || folder.isEmpty() || folder.equals("undefined") || folder.equals("null")){
                folder = "default";
            }
            String towerMark = SubstringUtil.substring1(folder);

            ExifUtil exifUtil = new ExifUtil();
            Map exifMap =    exifUtil.readPicExifInfo(file);
            double latitude = 0.0, longitude = 0.0;
            if (exifMap.containsKey("GPS Latitude")) {
                // 假设 latLng2Decimal 方法能正确解析经纬度字符串为double
                latitude = exifUtil.latLng2Decimal((String) exifMap.get("GPS Latitude"));
            }
            if (exifMap.containsKey("GPS Longitude")) {
                longitude = exifUtil.latLng2Decimal((String) exifMap.get("GPS Longitude"));
            }
            System.out.println( "经度"+latitude+"  纬度"+longitude);

            // 文件流
            String url = applicationName + "/" + ucId + "/" + folder+"/" + fileName;
            InputStream inputStream = file.getInputStream();
            if (!minioService.putObject(bucketName, url, inputStream,"kmz")) {
                return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
            }
            url = minioService.getPresignedObjectUrl(bucketName, url);
            if ("".equals(url)) {
                return ResultUtil.error("保存文件失败(错误码 4)！");
            }
            // 直接调用枚举中的方法获取媒体列表
            EfPhoto efPhoto= efMediaService.uploadExifPhotoFile(file,  user, 1,createTime,url,latitude,longitude ,towerMark);
            return ResultUtil.success("success",efPhoto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }
    }


    /**
     *
     * @param uploadTypeStr 媒体类型 正射图集
     * @param file 上传的文件
     * @param createTime 上传时间
     * @param folder 文件夹名
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{uploadType}/mapupload")
    public Result getOrthoimgLists(@PathVariable("uploadType") String uploadTypeStr, @RequestParam(value = "file",required = true) MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime, @RequestParam(value = "folder", required = false) String folder,@RequestParam(value = "fileNum", required = true) Long fileNum,@RequestParam("filemd5") String  filemd5,@RequestParam("overallMD5") String  overallMD5){
        InputStream inputStream = null;
        try {
            EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
            String bucketName = mediaType.toString();
            // 使用线程上传文件到minio
            // 获取文件名
            String fileName = file.getOriginalFilename();
            if(folder == null || folder.isEmpty() || folder.equals("undefined") || folder.equals("null")){
                folder = "default";
            }
            //folder = /202408160833_001_B001 截取获取 最后 B001 作为文件夹名
            String towerMark = SubstringUtil.substring1(folder);

            String[]  folderArr2 = folder.split("/");
            String parentFolder = folderArr2[0];
            // parentFolder 为空字符串 folderArr2.length>2 取 folderArr2[1]
            if(parentFolder.isEmpty() && folderArr2.length>1){
                parentFolder = folderArr2[1];
            }
            if ("result.tfw".equals(fileName)) {
                readFile(overallMD5,file);
            }
                // 文件流
            inputStream = file.getInputStream();
            String url = applicationName +"/"+parentFolder+overallMD5 + "/" + folder+"/" + fileName;
            //判断是否上传过  "a_" + parentFolder + "_" + overallMD5   filemd5
            Object exitObj = redisUtils.hmGet("a_" + parentFolder + "_" + overallMD5 ,  filemd5);
            if(exitObj==null){
                if (minioService.putObject(bucketName, url, inputStream,"kmz")) {
//                    return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
                    url = minioService.getPresignedObjectUrl(bucketName, url);
                }
            }
            int value = 0;
            boolean finshed = false;
            RLock lock = redissonClient.getLock("filelist_upload_lock");
            lock.lock();
            try{
               boolean flag =  redisUtils.isHashExists("a_" + parentFolder + "_" + overallMD5, overallMD5,"0",24,TimeUnit.HOURS); // 默认请求上传第一次
                // 是否存在
                if(flag){
                Object currentFileNum =  redisUtils.hmGet("a_" + parentFolder + "_" + overallMD5, overallMD5); // 获取当前上传文件序号
                    // 尝试将当前值转换为整数
                 value = Integer.parseInt(currentFileNum.toString())+1;
                 redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5, overallMD5,value+"",24,TimeUnit.HOURS); // 上传文件序号+1
                }
                if(exitObj==null){
                    redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5, filemd5,url,24,TimeUnit.HOURS);
                }
                if(value ==fileNum ){
                    redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5,  overallMD5,0+"",2,TimeUnit.HOURS); // 上传文件序号+1
                    if(exitObj!=null){
                        url = (String)exitObj;
                    }
                    System.out.println("上传完成:"+url);
                    finshed = true;
                }
            }finally {
                lock.unlock(); // 释放锁
            }
            if(finshed){
                String mapPath = SubstringUtil.processUrl(url,Endpoint,EndpointExt);
               Location location = fileContentHolder.getFileContent(overallMD5);
               EfOrthoImg efOrthoImg = efMediaService.uploadOrthoImgMap(overallMD5,  location.getLatitude(),location.getLongitude(), towerMark,createTime,mapPath, parentFolder);
                return ResultUtil.success("success",efOrthoImg);
            }
            return ResultUtil.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败1！");
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LogUtil.logError("关闭文件流失败：" + e.toString());
            }
        }
    }

    public  void readFile(String MD5,MultipartFile file) {
        if(file == null || file.isEmpty()){
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int count = 0;
            Location location = new Location();
            while ((line = br.readLine())!= null) {
                count++;
                if(count == 6){
                    location.setLatitude(Double.parseDouble(line));
                }
                if(count == 5){
                    location.setLongitude(Double.parseDouble(line));
                }
            }
            fileContentHolder.setFileContent(MD5,location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     * @param uploadTypeStr 媒体类型 正射图集
     * @param file 上传的文件
     * @param createTime 上传时间
     * @param folder 文件夹名
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{uploadType}/uploadwebcloud")
    public Result uploadCloudLists(@PathVariable("uploadType") String uploadTypeStr, @RequestParam(value = "file",required = true) MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime, @RequestParam(value = "folder", required = false) String folder,@RequestParam(value = "fileNum", required = true) Long fileNum,@RequestParam("filemd5") String  filemd5,@RequestParam("overallMD5") String  overallMD5){
        InputStream inputStream = null;
        try {
            EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
            String bucketName = mediaType.toString();
            // 使用线程上传文件到minio
            // 获取文件名
            String fileName = file.getOriginalFilename();
            if(folder == null || folder.isEmpty() || folder.equals("undefined") || folder.equals("null")){
                folder = "default";
            }
            //folder = /202408160833_001_B001 截取获取 最后 B001 作为文件夹名
            String towerMark = SubstringUtil.substring1(folder);

            String[]  folderArr2 = folder.split("/");
            String parentFolder = folderArr2[0];
            // parentFolder 为空字符串 folderArr2.length>2 取 folderArr2[1]
            if(parentFolder.isEmpty() && folderArr2.length>1){
                parentFolder = folderArr2[1];
            }
            boolean getURL = false;
            if(fileName.contains("tileset.json")){
                getURL = true;
            }

            if ("result.tfw".equals(fileName)) {
                readFile(overallMD5,file);
            }
            // 文件流
            inputStream = file.getInputStream();
            String url = applicationName +"/"+parentFolder+overallMD5 + "/" + folder+"/" + fileName;
            //判断是否上传过  "a_" + parentFolder + "_" + overallMD5   filemd5
            Object exitObj = redisUtils.hmGet("a_" + parentFolder + "_" + overallMD5 ,  filemd5);
            if(exitObj==null){
                if (minioService.putObject(bucketName, url, inputStream,"kmz")) {
//                    return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
                    url = minioService.getPresignedObjectUrl(bucketName, url);
                }
            }
            int value = 0;
            boolean finshed = false;
            RLock lock = redissonClient.getLock("filelist_upload_lock");
            lock.lock();
            try{
                boolean flag =  redisUtils.isHashExists("a_" + parentFolder + "_" + overallMD5, overallMD5,"0",24,TimeUnit.HOURS); // 默认请求上传第一次
                // 是否存在
                if(flag){
                    Object currentFileNum =  redisUtils.hmGet("a_" + parentFolder + "_" + overallMD5, overallMD5); // 获取当前上传文件序号
                    // 尝试将当前值转换为整数
                    value = Integer.parseInt(currentFileNum.toString())+1;
                    redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5, overallMD5,value+"",24,TimeUnit.HOURS); // 上传文件序号+1
                }
                if(exitObj==null){
                    redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5, filemd5,url,24,TimeUnit.HOURS);
                }
                if(value ==fileNum ){
                    redisUtils.hmSet("a_" + parentFolder + "_" + overallMD5,  overallMD5,0+"",2,TimeUnit.HOURS); // 上传文件序号+1
                    if(exitObj!=null){
                        url = (String)exitObj;
                    }
                    System.out.println("上传完成:"+url);
                    finshed = true;
                }
            }finally {
                lock.unlock(); // 释放锁
            }
            if(finshed){
                String mapPath = SubstringUtil.processUrl(url,Endpoint,EndpointExt);
                Location location = fileContentHolder.getFileContent(overallMD5);
                EfOrthoImg efOrthoImg = efMediaService.uploadOrthoImgMap(overallMD5,  location.getLatitude(),location.getLongitude(), towerMark,createTime,mapPath, parentFolder);
                return ResultUtil.success("success",efOrthoImg);
            }
            return ResultUtil.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败1！");
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LogUtil.logError("关闭文件流失败：" + e.toString());
            }
        }
    }

    /**
     *
     * @param uploadTypeStr 媒体类型 点云
     * @param user 当前登录用户
     * @param file 上传的文件
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{uploadType}/uploads2")
    public Result getCloud(@PathVariable("uploadType") String uploadTypeStr, @CurrentUser EfUser user, @RequestParam(value = "file",required = true) MultipartFile file, @RequestParam(value = "createTime", required = true) Date createTime){
        InputStream inputStream = null;

        boolean isZIP = isCompressedFile(file.getOriginalFilename());
        if (!isZIP) {
            return ResultUtil.error("请发送数据压缩包");
        }
        String url = null;
        long fileSize = file.getSize();
        long amendSize = 0;
        //压缩包文件名除去后缀
        String zipName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        Integer ucId = user.getId();
            // 将 MultipartFile 转换为字节数组输入流
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());
                 ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream, Charset.forName("GBK"))) {
                EfMediaType mediaType = EfMediaType.valueOf(uploadTypeStr.toUpperCase()); // 上传类型
                String bucketName = mediaType.toString();
                ZipEntry zipEntry ;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    if (zipEntry.isDirectory()) {
                        System.out.println("This is a directory");
                        continue;
                    }
                    boolean getURL = false;
                    // 获取压缩包内每一个文件的文件流 存储minio
                    String fileName = zipEntry.getName(); // tileset.json
                    // 文件名是否含有 tileset.json
                    if(fileName.contains("tileset.json")){
                        getURL = true;
                    }
                    // 获取文件后缀
                    String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
                    // 加时间为文件夹名
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String folderName = sdf.format(new Date());
                    // 文件夹名
                    url = applicationName + "/" + ucId + "/" + folderName + "/" + fileName;
                    //获取压缩包内文件流
                    inputStream = zipInputStream;
                    // 文件大小
                    amendSize = inputStream.available();
                    if (!minioService.putObject(bucketName, url, inputStream,suffix)) {
                        return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
                    }
                    if(getURL){
                        url = minioService.getPresignedObjectUrl(bucketName, url);
                        if ("".equals(url)) {
                        return ResultUtil.error("保存文件失败(错误码 4)！");
                       }
                    }
                }
                EfPointCloud pointCloud = new EfPointCloud();
                pointCloud.setMark(zipName);
                pointCloud.setAmendCloudUrl(url);
                pointCloud.setCreateTime(createTime);
                pointCloud.setAmendSize(amendSize);
                pointCloud.setAmendType("json");
                pointCloud.setCreateTime(createTime);
                pointCloud.setOriginSize(fileSize);
              pointCloud =    efMediaService.insertPointCloud(pointCloud);

                return ResultUtil.success("success",pointCloud);
            }catch (Exception e){
                e.printStackTrace();
                return ResultUtil.error("压缩包上传失败！");
            }
    }



    public static boolean isCompressedFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("zip") ||
                extension.equalsIgnoreCase("rar") ||
                extension.equalsIgnoreCase("7z");
    }



    public void resizeTiff(InputStream inputStream, OutputStream outputStream) {
        try {
            // 打印 inputStream.available() 的值来检查可用字节数

            int available = inputStream.available();
            System.out.println("Available bytes: "+available);

            BufferedImage originalImage = ImageIO.read(inputStream);
            int newWidth = 1075;
            int newHeight = 927;
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(resizedImage, "TIFF", outputStream);
            System.out.println("TIFF file resized and saved to: " );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void resizeTiffs(String inputPath, String outputPath, int newWidth, int newHeight) {
        try {
            // 读取原始 TIFF 文件
            BufferedImage originalImage = ImageIO.read(new File(inputPath));
            // 获取原始图像的宽度和高度
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // 计算缩放比例来调整图像大小 如果缩放比例小于10倍，则使用双线性插值 (Image.SCALE_SMOOTH) 进行缩放 否则使用最近邻插值 (Image.SCALE_FAST)
            double scale = Math.min(newWidth / (double) originalWidth, newHeight / (double) originalHeight);
            if (scale < 0.1) {
                scale = 0.1;
            }
            int scaledWidth = (int) (originalWidth * scale);
            int scaledHeight = (int) (originalHeight * scale);

            // 调整图像大小
            Image originalImages = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            // 创建一个新的支持透明度的 BufferedImage 来存储调整后的图像
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

            // 获取图形上下文
            Graphics2D g2d = resizedImage.createGraphics();
            // 将背景设置为透明
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, newWidth, newHeight);
            g2d.setComposite(AlphaComposite.SrcOver);
            // 绘制缩放后的图像
            g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();

            // 使用 LZW 或其他压缩算法写入 TIFF 文件
            // 注意：ImageIO 默认可能不支持 TIFF 的 LZW 压缩，这可能需要额外的库或自定义处理
            // 这里我们仅使用 ImageIO 的默认行为，它可能会使用 JPEG 或其他压缩
            ImageIO.write(resizedImage, "TIFF", new File(outputPath));

            System.out.println("TIFF file resized and saved to: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static byte[] downloadFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream(); //
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }



}

