package com.bear.reseeding.service;

import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;

import java.util.List;

public interface EfBusinessService {

    List<EfTower> getTowerList(String startDate, String endDate, String mark);

    List<EfPerilPoint> getDangerPointList(String startDate, String endDate, String mark);

    EfTower insertOrUpdate (EfTower tower);

    List<EfTower> batchInsertTower(List<EfTower> towerList);

    EfPerilPoint insertOrUpdate (EfPerilPoint perilPoint);
}
