package com.bear.reseeding.service;

import com.bear.reseeding.entity.EfTaskKmz;

import java.util.List;

public interface RouteService {


    List<EfTaskKmz> queryAllByLimit(int offset, int limit);

    EfTaskKmz saveKmz(EfTaskKmz kmz);

//    EfTaskKmz queryById(int id);
}

