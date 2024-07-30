package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfBusinessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    //#region 注解
    @Resource
    private EfBusinessService efBusinessService;

    //#endregion

    //#region 杆塔业务相关接口


    //获取杆塔列表 startTime 开始时间 前三个月时间戳  endTime 结束时间 当前时间戳
    // 获取当前时间戳的方法
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    // 获取前三个月时间戳的方法
    public static long getThreeMonthsAgoTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTimeInMillis();
    }

    @RequestMapping(value = "/tower/querylist", method = RequestMethod.POST)
    public Result getBusinessData(@RequestParam(value = "startTime", required = false) Long startTime, @RequestParam(value = "endTime", required = false) Long endTime, @RequestParam(value = "mark", required = false) String mark) throws ParseException {
        // mark 为空字符串 或者 null 或者“” 或者 undefined 或 字符串null 则 设置成 null
        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
            mark = null;
        }
        // 如果endTime未提供或为0（作为特殊标记），则设置为当前时间的时间戳
        if (endTime == null || endTime == 0) {
            endTime = Instant.now().toEpochMilli();
        }
        // 如果startTime未提供，则计算为当前时间前三个月的时间戳
        if (startTime == null) {
            startTime = Instant.now().minus(3, ChronoUnit.MONTHS).toEpochMilli();
        }
        if (startTime > endTime) {
            return ResultUtil.error("开始时间不能大于结束时间！");
        }
        try {
            Date startTimeDate = new Date(startTime);
            Date endTimeDate = new Date(endTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeStr = sdf.format(startTimeDate);
            String endTimeStr = sdf.format(endTimeDate);
            List<EfTower> towerList = efBusinessService.getTowerList(startTimeStr, endTimeStr, mark);
            // 返回结果
            return ResultUtil.success("success", towerList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取杆塔列表失败！");
        }
    }


    //#endregion获取业务数据

    // #region 危险点相关接口

    /**
     * 获取危险点列表  参数：startTime 开始时间 前三个月时间戳  endTime 结束时间 当前时间戳  mark 特殊标记 可选 （默认三个月数据
     *
     * @param startTime
     * @param endTime
     * @param mark
     * @return
     */
    @RequestMapping(value = "/dangerPoint/querylist", method = RequestMethod.POST)
    public Result getDangerPointList(@RequestParam(value = "startTime", required = false) Long startTime, @RequestParam(value = "endTime", required = false) Long endTime, @RequestParam(value = "mark", required = false) String mark) {
        // mark 为空字符串 或者 null 或者“” 或者 undefined 或 字符串null 则 设置成 null
        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
            mark = null;
        }
        // 如果endTime未提供或为0（作为特殊标记），则设置为当前时间的时间戳
        if (endTime == null || endTime == 0) {
            endTime = Instant.now().toEpochMilli();
        }
        // 如果startTime未提供，则计算为当前时间前三个月的时间戳
        if (startTime == null) {
            startTime = Instant.now().minus(3, ChronoUnit.MONTHS).toEpochMilli();
        }
        if (startTime > endTime) {
            return ResultUtil.error("开始时间不能大于结束时间！");
        }
        try{
            Date startTimeDate = new Date(startTime);
            Date endTimeDate = new Date(endTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeStr = sdf.format(startTimeDate);
            String endTimeStr = sdf.format(endTimeDate);
            List<EfPerilPoint> efPerilPointList = efBusinessService.getDangerPointList(startTimeStr, endTimeStr, mark);
            return ResultUtil.success("success", efPerilPointList); // 返回结果
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }

    }
    //#endregion 获取危险点列表


}
