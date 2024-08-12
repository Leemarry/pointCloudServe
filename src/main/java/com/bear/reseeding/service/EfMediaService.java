package com.bear.reseeding.service;

import com.bear.reseeding.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface EfMediaService {


    List<EfPhoto> getPhotolist(String startDate, String endDate, String mark);
    List<EfVideo> getVideolist(String startDate, String endDate, String mark);

    List<EfPointCloud> getCloudlist(String startDate, String endDate, String mark);

    List<EfOrthoImg> getOrthoImglist(String startDate, String endDate, String mark);

    List<EfReport> getReportlist(String startDate, String endDate, String mark);

    List<EfReport> getReportlistByType(String startDate, String endDate, String mark,Integer type);

    EfPhoto uploadPhotoFile(MultipartFile file, EfUser user , Integer type, Date createTime,String url);

    EfVideo uploadVideoFile(MultipartFile file, EfUser user , Integer type, Date createTime,String url);

    EfOrthoImg uploadOrthoImgFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url);

    EfPointCloud uploadPointCloudFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url);

    EfReport uploadReportFile(MultipartFile file, EfUser user, Integer type, Date createTime, String url);

    EfPointCloud insertPointCloud (EfPointCloud pointCloud);

    EfPointCloud insertOrUpdatePointCloud (EfPointCloud pointCloud);
    }
