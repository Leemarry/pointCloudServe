package com.bear.reseeding.serviceImpl;

import com.bear.reseeding.dao.EfBusinessDao;
import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfTowerLine;
import com.bear.reseeding.service.EfBusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("efTowerService")
public class EfBusinessServiceImpl implements EfBusinessService {

    @Resource
    private EfBusinessDao efBusinessDao;

    @Override
    public int gettowerCount(){
        return efBusinessDao.gettowerCount();
    }


    @Override
    public List<EfTower> getTowerAllInfoList(String startDate, String endDate, String mark) {
        List<EfTower> efTowers=  efBusinessDao.getTowerAllInfoList(startDate, endDate, mark);
        return efTowers;
    }

    @Override
    public List<EfTower> getTowerList(String startDate, String endDate, String mark) {
      List<EfTower> efTowers=  efBusinessDao.getTowerList(startDate, endDate, mark);
        return efTowers;
    }

    @Override
    public int delectTower(int id){
       return efBusinessDao.delectTower(id);
    }

    @Override
    public  int delectTowerLine(int id){
        return efBusinessDao.delectTowerLine(id);
    }

    @Override
    public List<EfTowerLine> getTowerLineList(String startDate, String endDate, String mark) {
        List<EfTowerLine> efTowerLines=  efBusinessDao.getTowerLineList(startDate, endDate, mark);
        return efTowerLines;
    }

    @Override
    public List<EfPerilPoint> getDangerPointList(String startDate, String endDate, String mark) {
        List<EfPerilPoint> efPerilPoints=  efBusinessDao.getDangerPointList(startDate, endDate, mark);
        return efPerilPoints;
    }

    @Override
    public EfTower insertTower(EfTower efTower) {
        efBusinessDao.insertTower(efTower);
        return efTower;
    }

    @Override
    public EfTower insertOrUpdate(EfTower efTower) {
        efBusinessDao.insertOrUpdateTower(efTower);
        return efTower;
    }
    @Override
    public EfTowerLine addOrupdateLine(EfTowerLine efTowerLine) {
        efBusinessDao.addOrupdateLine(efTowerLine);
        return efTowerLine;
    }
    @Override
    public List<EfTower> batchInsertTower(List<EfTower> efTowerList) {
        efBusinessDao.batchInsertTower(efTowerList);
        return efTowerList;
    }

    @Override
    public List<EfTower> batchInsertOrUpdateTower(List<EfTower> efTowerList) {
        efBusinessDao.batchInsertOrUpdateTower(efTowerList);
        return efTowerList;
    }

    @Override
    public List<EfTowerLine> batchInsertTowerLine(List<EfTowerLine> efTowerLineList) {
        int res =  efBusinessDao.batchInsertLine(efTowerLineList);
        return efTowerLineList;
    }

    @Override
    public EfPerilPoint insertOrUpdate(EfPerilPoint efPerilPoint) {
        efBusinessDao.insertOrUpdatePerilPoint(efPerilPoint);
        return efPerilPoint;
    }

    @Override
    public List<EfTower> queryTowerBymark(String mark) {
        return efBusinessDao.queryTowerBymark(mark);
    }

    @Override
    public List<EfTowerLine> queryTowerlineBymark(String mark) {
        return efBusinessDao.queryTowerlineBymark(mark);
    }

}
