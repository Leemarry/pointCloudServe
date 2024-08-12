package com.bear.reseeding.serviceImpl;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.dao.EfMediaDao;
import com.bear.reseeding.entity.*;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfMediaService;
import com.bear.reseeding.task.MinioService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service("EfMediaService")
public class EfMediaServiceImpl implements EfMediaService {

    @Resource
    private EfMediaDao efMediaDao;
    @Value("${spring.application.name}")
    private String applicationName;
    /**
     * minio
     */
    @Resource
    private MinioService minioService;

    public List<EfPhoto> getPhotolist(String startDate, String endDate, String mark){
        return efMediaDao.getPhotolist(startDate, endDate, mark);
    }

    public List<EfVideo> getVideolist(String startDate, String endDate, String mark){
        return efMediaDao.getVideolist(startDate, endDate, mark);
    }

    public List<EfPointCloud> getCloudlist(String startDate, String endDate, String mark){
        return efMediaDao.getPointcloudlist(startDate, endDate, mark);
    }
    public List<EfOrthoImg> getOrthoImglist(String startDate, String endDate, String mark){
        return efMediaDao.getOrthoImglist(startDate, endDate, mark);
    }
    public List<EfReport> getReportlist(String startDate, String endDate, String mark) {
        return efMediaDao.getReportlist(startDate, endDate, mark,null);
    }
    public List<EfReport> getReportlistByType(String startDate, String endDate, String mark,Integer type) {
        return efMediaDao.getReportlist(startDate, endDate, mark,type);
    }
//    public Result beforeUploadFile(MultipartFile file, EfUser user, String  type, Date createTime) {
//        Integer ucId = user.getId();
//        try{
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            // 获取文件后缀
//            String suffix = fileName.substring(fileName.lastIndexOf("."));
//            //文件大小
//            long fileSize = file.getSize();
//            // 文件流
//            String url = applicationName + "/"+type+"/" + ucId + "/" + fileName;
//            InputStream inputStream = file.getInputStream();
//            if (!minioService.putObject("kmz", url,inputStream,type)) {
//                return ResultUtil.error("保存文件失败(保存minio有误)！"); //生成kmzminio有误
//            }
//            EfMinioType efMinioType = EfMinioType.valueOf(type);
//            efMinioType.performAction();
//            url = minioService.getPresignedObjectUrl(BucketNameKmz, url);
//            if ("".equals(url)) {
//                return ResultUtil.error("保存文件失败(错误码 4)！");
//            }
//            return null;
//        } catch (Exception e){
//            return ResultUtil.error("保存文件失败(错误码 3)！");
//        }
//    }
    public EfOrthoImg uploadOrthoImgFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime, String url) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);   //获取文件后缀
        // 获取文件名除去后缀
        String name = fileName.substring(0, fileName.lastIndexOf("."));   //获取文件名除去后缀
        // 文件路径
        //文件大小
        long fileSize = multipartFile.getSize();
        EfOrthoImg orthoImg = new EfOrthoImg();
        orthoImg.setCreateTime(createTime);
        orthoImg.setMark(name);
        orthoImg.setPath(url);
        orthoImg.setSize(fileSize); // 文件大小
        orthoImg.setFormats(suffix);
        orthoImg.setScalePath(url);
        orthoImg.setScaleSize(fileSize);
        efMediaDao.insertOrthoImg(orthoImg);

        return orthoImg;
    }

    public EfPointCloud uploadPointCloudFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime, String url) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 获取文件名除去后缀
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        // 文件路径
        //文件大小
        long fileSize = multipartFile.getSize();
        EfPointCloud pointCloud = new EfPointCloud();
        pointCloud.setCreateTime(createTime);
        pointCloud.setMark(name);

        return pointCloud;
    }

    public EfPhoto uploadPhotoFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime ,String url) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 获取文件名除去后缀
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        // 文件路径
        //文件大小
        long fileSize = multipartFile.getSize();
        EfPhoto photo = new EfPhoto();
        photo.setCreateTime(createTime);
        photo.setMark(name);
        photo.setPath(url);
        photo.setSize(fileSize);
        photo.setImageTag(fileName);
        photo.setFormats("image");
        efMediaDao.insertPhoto(photo);

        return photo;
    }

    public EfVideo uploadVideoFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime, String url) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 获取文件名除去后缀
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        // 文件路径
        //文件大小
        long fileSize = multipartFile.getSize();
        EfVideo video = new EfVideo();
        video.setCreateTime(createTime);
        video.setMark(name);
        video.setPath(url);
        video.setSize(fileSize);
        video.setFormats("video");
        efMediaDao.insertVideo(video);

        return video;
    }


    public EfReport uploadReportFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime, String url) {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1); // 获取文件后缀 去除.
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        long fileSize = multipartFile.getSize();
        EfReport report = new EfReport();
        report.setCreateTime(createTime);
        report.setMark(name);
        report.setPath(url);
        report.setSize(fileSize);
        report.setType(type);
        report.setFormats(suffix);
        efMediaDao.insertReport(report);


        return report;
    }


    public EfPointCloud insertPointCloud (EfPointCloud pointCloud) {
        efMediaDao.insertPointCloud(pointCloud);
        return pointCloud;
    }

    public  EfPointCloud insertOrUpdatePointCloud(EfPointCloud pointCloud) {
        efMediaDao.insertOrUpdatePointCloud(pointCloud);
        return pointCloud;
    }


}
