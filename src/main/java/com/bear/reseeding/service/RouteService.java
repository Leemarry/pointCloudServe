package com.bear.reseeding.service;

import com.bear.reseeding.entity.EfKmz;
import com.bear.reseeding.entity.EfTaskKmz;

import java.util.List;

public interface RouteService {


    List<EfTaskKmz> queryAllByLimit(int offset, int limit);

    List<EfKmz> queryAllByLimit2(int offset, int limit);

    List<EfTaskKmz> queryAllByTime(String start, String end);

    List<EfKmz> queryAllByTime2(String start, String end);

    EfTaskKmz saveKmz(EfTaskKmz kmz);

    EfKmz saveKmz(EfKmz kmz);

    int deleteKmz(int id);

//    EfTaskKmz queryById(int id);
}

