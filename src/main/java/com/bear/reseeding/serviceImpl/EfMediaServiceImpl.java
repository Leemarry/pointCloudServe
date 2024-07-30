package com.bear.reseeding.serviceImpl;

import com.bear.reseeding.dao.EfMediaDao;
import com.bear.reseeding.entity.*;
import com.bear.reseeding.service.EfMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("EfMediaService")
public class EfMediaServiceImpl implements EfMediaService {

    @Resource
    private EfMediaDao efMediaDao;

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

}
