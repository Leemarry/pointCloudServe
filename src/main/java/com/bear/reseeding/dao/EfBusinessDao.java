package com.bear.reseeding.dao;

import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfTowerLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EfBusinessDao {
    int gettowerCount();

    List<EfTower> getTowerAllInfoList(@Param("startDate") String startDate, @Param("endDate") String endDate ,@Param("mark") String mark) ;

    List<EfTower> getTowerList(@Param("startDate") String startDate, @Param("endDate") String endDate ,@Param("mark") String mark) ;

    int delectTower(@Param("id") int id);

    int delectTowerLine(@Param("id") int id);

    List<EfTowerLine> getTowerLinesByTowerMark(@Param("towerMark") String towerMark) ;

    List<EfTowerLine> getTowerLineList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark) ;

    List<EfPerilPoint> getDangerPointList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("mark") String mark) ;


    int insertTower(EfTower tower);

    int insertOrUpdateTower(EfTower tower);

    int addOrupdateLine(EfTowerLine towerLine);

    int batchInsertTower(@Param("towerList") List<EfTower> towerList);

    int batchInsertOrUpdateTower(@Param("towerList") List<EfTower> towerList);

    int batchInsertLine(@Param("lineList") List<EfTowerLine> lineList);

    int insertOrUpdatePerilPoint(EfPerilPoint perilPoint);

    List<EfTower> queryTowerBymark(@Param("mark") String mark);

    List<EfTowerLine> queryTowerlineBymark(@Param("mark") String mark);
}


