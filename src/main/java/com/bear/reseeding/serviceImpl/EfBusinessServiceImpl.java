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
    public EfPerilPoint insertOrUpdate(EfPerilPoint efPerilPoint) {
        efBusinessDao.insertOrUpdatePerilPoint(efPerilPoint);
        return efPerilPoint;
    }

}
