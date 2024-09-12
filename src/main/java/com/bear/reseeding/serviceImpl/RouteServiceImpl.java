package com.bear.reseeding.serviceImpl;

import com.bear.reseeding.dao.EfTaskKmzDao;
import com.bear.reseeding.entity.EfKmz;
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

    public List<EfKmz> queryAllByLimit2( int offset,  int limit){
        return efTaskKmzDao.queryAllByLimit2(offset,  limit);
    }

  public List<EfTaskKmz> queryAllByTime(String startTime, String endTime){
      return efTaskKmzDao.queryAllByTime(startTime, endTime);
  }
    public List<EfKmz> queryAllByTime2(String startTime, String endTime){
        return efTaskKmzDao.queryAllByTime2(startTime, endTime);
    }


    public EfTaskKmz saveKmz(EfTaskKmz kmz){
        int num =   efTaskKmzDao.insert(kmz);
      if(num>0){
          return kmz;
      }
        return  null;
    }

    public EfKmz saveKmz(EfKmz kmz){
        int num =   efTaskKmzDao.insert2(kmz);
        if(num>0){
            return kmz;
        }
        return  null;
    }

    // deleteKmz

    public int deleteKmz(int id){
        int num = efTaskKmzDao.delete(id);
        return num;
    }


}
