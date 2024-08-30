package com.bear.reseeding.entity;

import com.bear.reseeding.service.EfMediaService;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

public enum EfMediaType {
    PHOTO {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getPhotolist(startTime, endTime, mark);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return efMediaService.uploadPhotoFile(file,  user, 1,createTime,url);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.delectphotoById(id);
        }
        @Override
        public  String toString(){
            return "efuav-image";
        }
    },
    VIDEO {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getVideolist(startTime, endTime, mark);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return efMediaService.uploadVideoFile(file,  user, towerMark,createTime,url);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteVideoById(id);
        }
        @Override
        public  String toString(){
            return "efuav-video";
        }
    },
    CLOUD {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getCloudlist(startTime, endTime, mark);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return "ssss";
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return  efMediaService.deletePointCloudById(id);
        }
        @Override
        public  String toString(){
            return "efuav-cloud";
        }
    },
    ORTHOIMG {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getOrthoImglist(startTime, endTime, mark);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return efMediaService.uploadOrthoImgFile(file,  user, 1,createTime,url);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteOrthoImgById(id);
        }
        @Override
        public  String toString(){
            return "efuav-ortho-img";
        }

    },
    REPORTS {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getReportlist(startTime, endTime, mark);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            // 返回空
            return "";
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteReportById(id);
        }
        @Override
        public  String toString(){
            return "efuav-reports";
        }
    },
    // perilPointReport
    PERIL_POINT_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getReportlistByType(startTime, endTime, mark,1);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return efMediaService.uploadReportFile(file,  user, 1,createTime,url,towerMark);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteReportById(id);
        }
        @Override
        public  String toString(){
            return "efuav-reports";
        }

    },
    // crossBorderReport
    CROSS_BORDER_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark){
            return efMediaService.getReportlistByType(startTime, endTime, mark,2);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user,String  towerMark, Date createTime, String url) {
            return efMediaService.uploadReportFile(file,  user, 2,createTime,url ,towerMark);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteReportById(id);
        }
        @Override
        public  String toString(){
            return "efuav-reports";
        }
    },
    // lineTowersAnalysisReport
    LINE_TOWERS_ANALYSIS_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark){
            return efMediaService.getReportlistByType(startTime, endTime, mark,3);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user,String  towerMark, Date createTime, String url) {
            return efMediaService.uploadReportFile(file,  user, 3,createTime,url,towerMark);
        }
        @Override
        public Integer delectMediaList(EfMediaService efMediaService, Integer id) {
            return efMediaService.deleteReportById(id);
        }
        @Override
        public  String toString(){
            return "efuav-reports";
        }
    };

    public abstract Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark);
    public abstract Integer delectMediaList(EfMediaService efMediaService,Integer id);
    public abstract Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url);
}
