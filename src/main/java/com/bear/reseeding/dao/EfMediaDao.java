package com.bear.reseeding.dao;

import com.bear.reseeding.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EfMediaDao {

    Integer deletepointCloudBytowermark(@Param("towerMark") String towerMark);

    Integer deleteOrthoImgBytowermark(@Param("towerMark") String towerMark);


    Integer deletePhotoBytowermark(@Param("towerMark") String towerMark);


    Integer deleteVideoBytowermark(@Param("towerMark") String towerMark);

    Integer delectphotoById(int id);

    Integer deleteOrthoImgById(int id);

    Integer deleteVideoById(int id);

    Integer deleteReportById(int id);

    Integer deletePointCloudById(int id);

    EfPhoto queryDistance(@Param("lat") double lat, @Param("lng") double lng);

    EfPhoto queryDistanceWithDis(@Param("lat") double lat, @Param("lng") double lng, @Param("dis") double dis);




    List<EfPhoto> getPhotosByTowerId(int towerId);

    List<EfPhoto> getPhotosByTowerMark(@Param("towerMark")   String towerMark);

    List<EfPhoto> getPhotosByTowerMark2(@Param("towerMark")   String towerMark);

    List<EfPhoto> getPhotolist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfVideo> getVideolist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark,@Param("fileName") String fileName);

    EfPointCloud getPointCloudByTowerMark(@Param("towerMark") String towerMark);

    EfPointCloud getPointCloudByTagAndMark(@Param("markTag") String markTag, @Param("markNum") int markNum);

    List<EfPointCloud> getPointcloudlist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    EfOrthoImg getOrthoImgByTowerMark(@Param("towerMark") String towerMark);

    EfOrthoImg getOrthoImgByTagAndNum(@Param("markTag") String markTag, @Param("markNum") int markNum);

    List<EfOrthoImg> getOrthoImglist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark);

    List<EfReport> getReportlist(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark,@Param("type") Integer type,@Param("fileName") String fileName);

    int insertOrthoImg(EfOrthoImg orthoImg);

    int insertPhoto(EfPhoto photo);

    int insertVideo(EfVideo video);

    int insertReport(EfReport report);

    int insertPointCloud(EfPointCloud pointcloud);

    EfPointCloud queryCloudByMark(@Param("towerMark") String towerMark);

    int insertOrUpdatePointCloud (EfPointCloud pointcloud);

    int insertOrUpdateOrthoImg (EfOrthoImg orthoImg);

    EfOrthoImg queryByMark(@Param("mark") String mark);

    EfPointCloud querycloudByMark(@Param("mark") String mark);

    EfPointCloud queryCloudByFormats(@Param("formats") String formats);
}
