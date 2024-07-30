package com.bear.reseeding.entity;

import com.bear.reseeding.service.EfMediaService;

public enum EfMediaType {
    PHOTO {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getPhotolist(startTime, endTime, mark);
        }
    },
    VIDEO {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getVideolist(startTime, endTime, mark);
        }
    },
    CLOUD {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getCloudlist(startTime, endTime, mark);
        }
    },
    ORTHOIMG {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getOrthoImglist(startTime, endTime, mark);
        }
    },
    REPORTS {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getReportlist(startTime, endTime, mark);
        }
    },
    // perilPointReport
    PERIL_POINT_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark) {
            return efMediaService.getReportlistByType(startTime, endTime, mark,1);
        }

    },
    // crossBorderReport
    CROSS_BORDER_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark){
            return efMediaService.getReportlistByType(startTime, endTime, mark,2);
        }
    },
    // lineTowersAnalysisReport
    LINE_TOWERS_ANALYSIS_REPORT {
        @Override
        public Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark){
            return efMediaService.getReportlistByType(startTime, endTime, mark,3);
        }
    };

    public abstract Object getMediaList(EfMediaService efMediaService, String startTime, String endTime, String mark);
}
