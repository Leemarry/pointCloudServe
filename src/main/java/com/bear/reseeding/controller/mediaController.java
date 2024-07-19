package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfMediaPhotoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("media")
public class mediaController {

    @Resource
    private EfMediaPhotoService efMediaPhotoService;
    //TODO:

    /**
     * 获取媒体库照片列表 根据传递的数据进行相应的查询
     * @param startTime
     * @param endTime
     * @param name  模糊查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/photo/querylist")
    public Result getPhotoList(@RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "name", required = false) String name){

//        if(name!= null && name.trim().length() > 0){
//            return efMediaPhotoService.getMediaPhotoListByName(name);
//        }
//        if(startTime!= 0 && endTime!= 0){
//            return efMediaPhotoService.getMediaPhotoListByTime(startTime, endTime);
//        }
//        return efMediaPhotoService.getMediaPhotoListLimit();
        return ResultUtil.success("sss");
    }

    /**
     * 获取媒体库视频列表 根据传递的数据进行相应的查询
     * @param startTime
     * @param endTime
     * @param name  模糊查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/video/querylist")
    public Result getVideoList(@RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "name", required = false) String name){

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
