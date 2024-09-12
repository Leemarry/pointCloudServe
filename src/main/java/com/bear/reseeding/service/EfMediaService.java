package com.bear.reseeding.service;

import com.bear.reseeding.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface EfMediaService {

    Integer deletepointCloudBytowermark(String towermark);

   Integer deletePhotoBytowermark(String towermark);

    Integer deleteVideoBytowermark(String towermark);

    Integer deleteOrthoImgBytowermark(String towermark);

    Integer delectphotoById(Integer id);

    Integer deleteVideoById(Integer id);

    Integer deleteOrthoImgById(Integer id);

    Integer deletePointCloudById(Integer id);

    Integer deleteReportById(Integer id);

    EfPhoto queryDistance( double lat, double lng);

    EfPhoto queryDistanceWithDis( double lat, double lng, double distance);

    List<EfPhoto> getPhotolist(String startDate, String endDate, String mark);
    List<EfVideo> getVideolist(String startDate, String endDate, String mark,String fileName);

    List<EfPointCloud> getCloudlist(String startDate, String endDate, String mark);

    List<EfOrthoImg> getOrthoImglist(String startDate, String endDate, String mark);

    List<EfReport> getReportlist(String startDate, String endDate, String mark,String fileName);

    List<EfReport> getReportlistByType(String startDate, String endDate, String mark,Integer type,String fileName);

    EfPhoto uploadPhotoFile(MultipartFile file, EfUser user , Integer type, Date createTime,String url);
    EfPhoto uploadExifPhotoFile(MultipartFile file, EfUser user , Integer type, Date createTime,String url , double lat, double lng , String towermark);

    EfVideo uploadVideoFile(MultipartFile file, EfUser user , String towermark, Date createTime,String url);

    EfOrthoImg uploadOrthoImgFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url);

    EfOrthoImg uploadOrthoImgMap(String md5,double lat,double lng,String towermark,Date createTime,String url ,String mark);

    EfPointCloud uploadPointCloudFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url);

    EfReport uploadReportFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url,String towerMark);

    EfPointCloud insertPointCloud (EfPointCloud pointCloud);
    EfPointCloud insertOrUpdatePointCloud (EfPointCloud pointCloud);

    EfOrthoImg insertOrUpdateOrthoImg (EfOrthoImg orthoImg);

    EfPointCloud queryCloudByMark(String towermark);

    EfPointCloud queryCloudByFormats(String formats);

    EfOrthoImg queryByMark(String mark);

    EfPointCloud querycloudByMark(String mark);

    }
