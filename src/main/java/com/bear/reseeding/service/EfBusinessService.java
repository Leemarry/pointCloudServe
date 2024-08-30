package com.bear.reseeding.service;

import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfTowerLine;

import java.util.List;

public interface EfBusinessService {

    int gettowerCount();

    List<EfTower> getTowerList(String startDate, String endDate, String mark);

    List<EfTower> getTowerAllInfoList(String startDate, String endDate, String mark);

    int delectTower(int id);
    int delectTowerLine(int id);

    List<EfTowerLine> getTowerLineList(String startDate, String endDate, String mark);

    List<EfPerilPoint> getDangerPointList(String startDate, String endDate, String mark);

    EfTower insertOrUpdate (EfTower tower);

    EfTowerLine addOrupdateLine(EfTowerLine towerLine);

    List<EfTower> batchInsertTower(List<EfTower> towerList);

    List<EfTowerLine> batchInsertTowerLine(List<EfTowerLine> towerLineList);

    EfPerilPoint insertOrUpdate (EfPerilPoint perilPoint);
}
