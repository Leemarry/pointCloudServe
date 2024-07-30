package com.bear.reseeding.service;

import com.bear.reseeding.entity.*;

import java.util.List;

public interface EfMediaService {


    List<EfPhoto> getPhotolist(String startDate, String endDate, String mark);
    List<EfVideo> getVideolist(String startDate, String endDate, String mark);

    List<EfPointCloud> getCloudlist(String startDate, String endDate, String mark);

    List<EfOrthoImg> getOrthoImglist(String startDate, String endDate, String mark);

    List<EfReport> getReportlist(String startDate, String endDate, String mark);

    List<EfReport> getReportlistByType(String startDate, String endDate, String mark,Integer type);

}
