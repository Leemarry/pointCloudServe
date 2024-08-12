package com.bear.reseeding.dao;

import com.bear.reseeding.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EfMediaDao {


    List<EfPhoto> getPhotosByTowerId(int towerId);

    List<EfPhoto> getPhotolist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfVideo> getVideolist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfPointCloud> getPointcloudlist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfOrthoImg> getOrthoImglist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfReport> getReportlist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark,@Param("type") Integer type);

    int insertOrthoImg(EfOrthoImg orthoImg);

    int insertPhoto(EfPhoto photo);

    int insertVideo(EfVideo video);

    int insertReport(EfReport report);

    int insertPointCloud(EfPointCloud pointcloud);

    int insertOrUpdatePointCloud (EfPointCloud pointcloud);
}
