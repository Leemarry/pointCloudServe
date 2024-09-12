package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.*;
import com.bear.reseeding.model.CurrentUser;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.RouteService;
import com.bear.reseeding.task.MinioService;
import com.bear.reseeding.utils.FileUtil;
import com.bear.reseeding.utils.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("route")
public class routeController {

    @Value("${minio.BucketNameKmz}")
    private String BucketNameKmz;
    @Value("${spring.application.name}")
    private String applicationName;
    @Resource
    private RouteService routeService;
    /**
     * minio
     */
    @Resource
    private MinioService minioService;

    @ResponseBody
    @PostMapping(value = "/kmz/querylistByTime")
    public Result getPhotoListbyTime(   @RequestParam(value = "startTime", required = false) Date startTime,
                                        @RequestParam(value = "endTime", required = false) Date endTime){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeStr = sdf.format(startTime);
            String endTimeStr = sdf.format(endTime);
            List<EfKmz> list = routeService.queryAllByTime2( startTimeStr,  endTimeStr); //获取所有kmz数据

            if(list.size() > 0){
                return ResultUtil.success("获取成功",list);
            }
            return ResultUtil.success("获取成功",list);
        }catch (Exception e){
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     *  获取kmz航线数据信息
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/kmz/querylist")
   public Result getPhotoList(@RequestParam(value = "offset", defaultValue = "0") int offset,@RequestParam(value = "limit", defaultValue = "10") int limit){
        try{
            List<EfKmz> list = routeService.queryAllByLimit2( offset,  limit); //获取所有kmz数据
            return ResultUtil.success("获取成功",list);
        }catch (Exception e){
            return ResultUtil.error(e.getMessage());
        }
    }


    @ResponseBody
    @PostMapping(value = "/kmz/upload")
    public  Result upload(@CurrentUser EfUser currentUser, @RequestPart ("files")  MultipartFile[] files){
        InputStream inputStream = null;
        try{
            if(files == null || files.length == 0){
                return ResultUtil.error("请选择文件");
            }
            Integer ucId = currentUser.getId();
            Integer userId = currentUser.getId();
            String userName = currentUser.getUName();
            List<EfKmz> efKmzList = new ArrayList<>();
            for(MultipartFile file : files){
                //#region 文件信息
                // 文件名
                String fileName = file.getOriginalFilename();
                // 文件大小
                long fileSize = file.getSize();
                 inputStream = file.getInputStream();
                String bucketName =  "efuav-kmz";
                String url = applicationName + "/kmzTasks/" + ucId + "/" + fileName;
                if (!minioService.putObject(bucketName, url, inputStream, "kmz")) {
                    return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
                }
                url = minioService.getProxyObjectUrl(bucketName, url);
                url = removeDuplicateSlashes(url);
                if ("".equals(url)) {
                    return ResultUtil.error("保存巡检航线失败(错误码 4)！");
                }
                EfKmz efKmz = new EfKmz();
                efKmz.setKmzPath(url);
                efKmz.setKmzSize(fileSize);
                efKmz.setCreateTime(new Date());
                efKmz.setKmzName(fileName);
                efKmz =   routeService.saveKmz(efKmz);
                efKmzList.add(efKmz);
            }
            return ResultUtil.success("上传成功",efKmzList);
        }catch (Exception e) {
            return ResultUtil.error(e.getMessage());
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





//    @ResponseBody
//    @PostMapping(value = "/kmz/upload")
//    public  Result upload(@CurrentUser EfUser currentUser, @RequestPart ("files")  MultipartFile[] files, @RequestPart("efTaskKmzs") @Validated List<EfTaskKmz> efTaskKmzs){
//        try{
//            if(files == null || files.length == 0){
//                return ResultUtil.error("请选择文件");
//            }
//            Integer ucId = currentUser.getId();
//            Integer userId = currentUser.getId();
//            String userName = currentUser.getUName();
//            List<EfTaskKmz> efTaskKmzList = new ArrayList<>();
//            for(MultipartFile file : files){
//                //#region 文件信息
//              // 文件名
//              String fileName = file.getOriginalFilename();
//              // 文件大小
//              long fileSize = file.getSize();
//              // 文件类型 通过文件后缀截取
//              String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
//              // 文件流
//              byte[] fileBytes = file.getBytes();
//              InputStream inputStream = file.getInputStream();
//              //#endregion
//              // 通过文件名fileName 在 efTaskKmzs中找到对应的EfTaskKmz对象
//              for(EfTaskKmz efTaskKmz : efTaskKmzs){
//                  if(fileName.equals(efTaskKmz.getKmzName())) {
//                      // 上传minion
//                      String bucketName =  "efuav-kmz";
//                      String url = applicationName + "/kmzTasks/" + ucId + "/" + fileName;
//                     // String url = applicationName + "/" + ucId + "/" + folder + "/" + fileName;
//                      url = removeDuplicateSlashes(url);
////                      if (!minioService.uploadfile("kmz", url, "kmz",inputStream)) {
////                          return ResultUtil.error("保存巡检航线失败(/生成kmzminio有误)！"); //生成kmzminio有误
////                      }
//                      if (!minioService.putObject(bucketName, url, inputStream, "kmz")) {
//                          return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
//                      }
//                      url = minioService.getProxyObjectUrl(BucketNameKmz, url);
//                      url = removeDuplicateSlashes(url);
//                      if ("".equals(url)) {
//                          return ResultUtil.error("保存巡检航线失败(错误码 4)！");
//                      }
//                      efTaskKmz.setKmzPath(url);
//                      efTaskKmz.setKmzSize(fileSize);
//                      efTaskKmz.setKmzType(fileType);
//                      efTaskKmz.setKmzName(fileName);
//                      efTaskKmz.setKmzCompanyId(ucId);
//                      efTaskKmz.setKmzUpdateByUserId(userId);
//                      efTaskKmz.setKmzCreateByUserId(userId);
//                  }
//                    EfTaskKmz kmz =   routeService.saveKmz(efTaskKmz);
//                  efTaskKmzList.add(kmz);
//                  // 将 efTaskKmz 从数组移除 防止重复上传
//                  efTaskKmzs.remove(efTaskKmz);
//                      break;
//              }
//            }
//            return ResultUtil.success("上传成功",efTaskKmzList);
//        }catch (Exception e) {
//            return ResultUtil.error(e.getMessage());
//        }
//    }



    @ResponseBody
    @PostMapping(value = "/kmz/delect")
    public  Result delect(@CurrentUser EfUser currentUser, @RequestParam("ids")  Integer[]  ids){
        try{
            if(ids == null || ids.length == 0){
                return ResultUtil.error("请选择要删除的记录");
            }
            List<Integer> succeedIds = new ArrayList<>();
            if (ids != null && ids.length > 0) {
                for (Integer id : ids) {
                    Integer res = routeService.deleteKmz(id);
                    if (res != null && res > 0) {
                        succeedIds.add(id);
                    }
                }
            }
            return ResultUtil.success("success",succeedIds);
        }catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/updateGoods")
    @ResponseBody
    public Result updateGoods(
            @RequestPart("meta")  List<Meta> meta,
            @RequestPart("file")  MultipartFile file) {
        System.out.println("meta"+meta);
        return ResultUtil.success();
    }

    public static String removeDuplicateSlashes(String path) {
        String regex = "(/{2,})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        return matcher.replaceAll("/");
    }

//    @ResponseBody
//    @PostMapping(value = "/kmz/upload")
//    public  Result uploads(@CurrentUser EfUser currentUser, @RequestParam("files")  MultipartFile[] files,@RequestParam  Map<String,String> params){
//        try{
//            if(files == null || files.length == 0){
//                return ResultUtil.error("请选择文件");
//            }
////            return routeService.upload(files);
//            return ResultUtil.success();
//        }catch (Exception e) {
//            return ResultUtil.error(e.getMessage());
//        }
//    }




}
