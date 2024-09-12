package com.bear.reseeding.entity;

import com.bear.reseeding.service.EfMediaService;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

public enum EfMediaType {
    PHOTO {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
            return efMediaService.getVideolist(startTime, endTime, mark,fileName);
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
            return efMediaService.getReportlist(startTime, endTime, mark,fileName);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url) {
            return efMediaService.uploadReportFile(file,  user, 0,createTime,url,towerMark);
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName) {
            return efMediaService.getReportlistByType(startTime, endTime, mark,1,fileName);
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName){
            return efMediaService.getReportlistByType(startTime, endTime, mark,2,fileName);
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
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName){
            return efMediaService.getReportlistByType(startTime, endTime, mark,3,fileName);
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
    },

    // line_analysis_report
    LINE_ANALYSIS_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName){
            return efMediaService.getReportlistByType(startTime, endTime, mark,4,fileName);
        }
        @Override
        public Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user,String  towerMark, Date createTime, String url) {
            return efMediaService.uploadReportFile(file,  user, 4,createTime,url,towerMark);
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

    public abstract Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark,String fileName);
    public abstract Integer delectMediaList(EfMediaService efMediaService,Integer id);
    public abstract Object uploadMediaList(EfMediaService efMediaService , MultipartFile file, EfUser user, String  towerMark, Date createTime, String url);
}
