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

    public Integer deletepointCloudBytowermark(String towermark) {
        return efMediaDao.deletepointCloudBytowermark(towermark);
    }
    public Integer deleteOrthoImgBytowermark(String towermark) {
        return efMediaDao.deleteOrthoImgBytowermark(towermark);
    }
    public Integer deletePhotoBytowermark(String towermark) {
        return efMediaDao.deletePhotoBytowermark(towermark);
    }
    public Integer deleteVideoBytowermark(String towermark) {
        return efMediaDao.deleteVideoBytowermark(towermark);
    }

    public Integer delectphotoById(Integer id) {
        return efMediaDao.delectphotoById(id);
    }
    public Integer deleteOrthoImgById(Integer id) {
        return efMediaDao.deleteOrthoImgById(id);
    }
    public Integer deleteVideoById(Integer id) {
        return efMediaDao.deleteVideoById(id);
    }
    public Integer deletePointCloudById(Integer id) {
        return efMediaDao.deletePointCloudById(id);
    }
    public Integer deleteReportById(Integer id) {
        return efMediaDao.deleteReportById(id);
    }

    public EfPhoto queryDistance(double lat, double lon){
        return efMediaDao.queryDistance(lat,lon);
    }


    public EfPhoto queryDistanceWithDis(double lat, double lon,double dis){
        return efMediaDao.queryDistanceWithDis(lat,lon,dis);
    }

//    public Integer deleteReportBytowermark(String towermark) {
//        return efMediaDao.deleteReportBytowermark(towermark);
//    }

    public List<EfPhoto> getPhotolist(String startDate, String endDate, String mark){
        return efMediaDao.getPhotolist(startDate, endDate, mark);
    }

    public List<EfVideo> getVideolist(String startDate, String endDate, String mark,String fileName){
        return efMediaDao.getVideolist(startDate, endDate, mark,fileName);
    }

    public List<EfPointCloud> getCloudlist(String startDate, String endDate, String mark){
        return efMediaDao.getPointcloudlist(startDate, endDate, mark);
    }
    public List<EfOrthoImg> getOrthoImglist(String startDate, String endDate, String mark){
        return efMediaDao.getOrthoImglist(startDate, endDate, mark);
    }
    public List<EfReport> getReportlist(String startDate, String endDate, String mark,String fileName) {
        return efMediaDao.getReportlist(startDate, endDate, mark,null,fileName);
    }
    public List<EfReport> getReportlistByType(String startDate, String endDate, String mark,Integer type,String fileName) {
        return efMediaDao.getReportlist(startDate, endDate, mark,type,fileName);
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

    public EfOrthoImg uploadOrthoImgMap(String md5, double lat , double lng,String towermark,Date createTime, String url ,String mark){
        EfOrthoImg orthoImg = new EfOrthoImg();
        orthoImg.setMapPath(url);
        orthoImg.setCreateTime(createTime);
        orthoImg.setLat(lat);
        orthoImg.setLon(lng);
        orthoImg.setTowerMark(towermark);
        orthoImg.setMapMd5(md5);
        orthoImg.setFormats("正射图级");
        orthoImg.setMark(mark);
        efMediaDao.insertOrthoImg(orthoImg);
        return orthoImg;
    }

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
        photo.setPath(url); //  http://localhost:9090/efuav-image/pointcloud/2/202408160833_001_B001/DJI_20231223122844_0059_D.JPG
        // 截取 url 中的B001
        int index = url.lastIndexOf("/");
        String towerMark = null;
        if (index > 0) {
            towerMark = url.substring(url.lastIndexOf("_", index)+1 ,index);
        }
        photo.setTowerMark(towerMark);
        photo.setSize(fileSize);
        photo.setImageTag(fileName);
        photo.setFormats("image");
        efMediaDao.insertPhoto(photo);

        return photo;
    }


    public EfPhoto uploadExifPhotoFile(MultipartFile multipartFile, EfUser user , Integer type, Date createTime,String url , double lat, double lng , String towermark) {
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
        photo.setTowerMark(towermark);
        photo.setSize(fileSize);
        photo.setImageTag(fileName);
        photo.setFormats("image");
        photo.setLat(lat);
        photo.setLng(lng);
        efMediaDao.insertPhoto(photo);

        return photo;
    }



    public EfVideo uploadVideoFile(MultipartFile multipartFile, EfUser user, String towermark, Date createTime, String url) {
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
        video.setTowerMark(towermark);
        video.setMark(fileName);
        video.setPath(url);
        video.setSize(fileSize);
        video.setFormats("video");
        efMediaDao.insertVideo(video);

        return video;
    }


    public EfReport uploadReportFile(MultipartFile multipartFile, EfUser user, Integer type, Date createTime, String url,String towerMark) {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1); // 获取文件后缀 去除.
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        long fileSize = multipartFile.getSize();
        EfReport report = new EfReport();
        report.setCreateTime(createTime);
        report.setMark(fileName);
        report.setPath(url);
        report.setSize(fileSize);
        report.setType(type);
        report.setFormats(suffix);
        report.setTowerMark(towerMark);
        efMediaDao.insertReport(report);


        return report;
    }


    public EfPointCloud insertPointCloud (EfPointCloud pointCloud) {
        efMediaDao.insertPointCloud(pointCloud);
        return pointCloud;
    }
    public EfPointCloud insertOrUpdatePointCloud (EfPointCloud pointCloud) {
        efMediaDao.insertOrUpdatePointCloud(pointCloud);
        return pointCloud;
    }
    public EfOrthoImg insertOrUpdateOrthoImg(EfOrthoImg orthoImg) {
        efMediaDao.insertOrUpdateOrthoImg(orthoImg);
        return orthoImg;
    }
    public EfPointCloud queryCloudByMark (String towerMark) {
      EfPointCloud pointCloud = efMediaDao.queryCloudByMark(towerMark);
        return pointCloud;
    }

    public EfOrthoImg queryByMark(String mark){
        return efMediaDao.queryByMark(mark);
    }
    public EfPointCloud querycloudByMark(String mark){
        return efMediaDao.querycloudByMark(mark);
    }
    public EfPointCloud queryCloudByFormats(String formats){
        return efMediaDao.queryCloudByFormats(formats);
    }



}
