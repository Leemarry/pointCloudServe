package com.bear.reseeding.dao;

import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfTowerLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EfBusinessDao {

    List<EfTower> getTowerList(@Param("startDate") String startDate, @Param("endDate") String endDate ,@Param("mark") String mark) ;

    int delectTower(@Param("id") int id);
    int delectTowerLine(@Param("id") int id);

    List<EfTowerLine> getTowerLineList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark) ;

    List<EfPerilPoint> getDangerPointList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark) ;

    int insertOrUpdateTower(EfTower tower);

    int addOrupdateLine(EfTowerLine towerLine);

    int batchInsertTower(@Param("towerList") List<EfTower> towerList);

    int insertOrUpdatePerilPoint(EfPerilPoint perilPoint);
}


