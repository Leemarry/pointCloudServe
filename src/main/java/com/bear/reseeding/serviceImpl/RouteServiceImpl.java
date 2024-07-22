package com.bear.reseeding.serviceImpl;

import com.bear.reseeding.dao.EfTaskKmzDao;
import com.bear.reseeding.entity.EfTaskKmz;
import com.bear.reseeding.service.RouteService;
import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Resource
    private EfTaskKmzDao efTaskKmzDao;


  public List<EfTaskKmz> queryAllByLimit( int offset,  int limit){
      return efTaskKmzDao.queryAllByLimit(offset,  limit);
  }

}
