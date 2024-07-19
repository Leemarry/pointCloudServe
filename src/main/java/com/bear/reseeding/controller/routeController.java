package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.RouteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("route")
public class routeController {

    @Resource
    private RouteService routeService;
//
//    /**
//     * 获取kmz航线数据信息
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST,value = "/getRoutes")
//    public Result getRoutes() {
////        return routeService.getRoutes();
//        return ResultUtil.success();
//    }


    @ResponseBody
    @PostMapping(value = "/kmz/querylist")
    public Result getPhotoList(){


//        if(name!= null && name.trim().length() > 0){
//            return efMediaPhotoService.getMediaPhotoListByName(name);
//        }
//        if(startTime!= 0 && endTime!= 0){
//            return efMediaPhotoService.getMediaPhotoListByTime(startTime, endTime);
//        }
//        return efMediaPhotoService.getMediaPhotoListLimit();
        return ResultUtil.success("sss");
    }

}
