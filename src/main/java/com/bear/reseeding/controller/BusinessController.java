package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfTowerLine;
import com.bear.reseeding.entity.EfUser;
import com.bear.reseeding.model.CurrentUser;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfBusinessService;
import com.bear.reseeding.service.EfMediaService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/business")
public class BusinessController {

    //#region 注解
    @Resource
    private EfBusinessService efBusinessService;
    @Resource
    private EfMediaService efMediaService;


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
    // 分页朝向
    @RequestMapping(value = "/tower/queryAlllist", method = RequestMethod.POST)
    public Result getTowerAllData(@RequestParam(value = "startTime", required = false) Long startTime, @RequestParam(value = "endTime", required = false) Long endTime, @RequestParam(value = "mark", required = false) String mark) throws ParseException {
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
            int totalCount = efBusinessService.gettowerCount();
            if(totalCount>0){
                List<EfTower> towerList = efBusinessService.getTowerAllInfoList(startTimeStr, endTimeStr, mark);
                return ResultUtil.success("success", towerList,totalCount);
            }
            // 返回结果
            return ResultUtil.success("success", null,0);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取杆塔列表失败！");
        }
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



    @RequestMapping(value = "/line/querylist", method = RequestMethod.POST)
    public Result getLineData(@RequestParam(value = "startTime", required = false) Long startTime, @RequestParam(value = "endTime", required = false) Long endTime, @RequestParam(value = "mark", required = false) String mark) throws ParseException {
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
            List<EfTowerLine> towerList = efBusinessService.getTowerLineList(startTimeStr, endTimeStr, mark);
            // 返回结果
            return ResultUtil.success("success", towerList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取杆塔列表失败！");
        }
    }


    @RequestMapping(value = "/{Type}/delect", method = RequestMethod.POST)
    public Result delectBusiness(@PathVariable("Type") String uploadTypeStr, @RequestParam("id") int id, @RequestParam(value = "mark", required = false) String mark,@RequestParam(value = "delectAll", required = false) boolean delectAll) {
        try {
            // tower i
            int result = 0;
            if (uploadTypeStr.equals("tower")) {
                if(delectAll&&mark!=null&&!mark.isEmpty() && !mark.equals("undefined")){
                    efMediaService.deletepointCloudBytowermark(mark); //
                    efMediaService.deleteVideoBytowermark(mark); //
                    efMediaService.deleteOrthoImgBytowermark(mark); //
                }
                efMediaService.deletePhotoBytowermark(mark); //
                 result = efBusinessService.delectTower(id);
            }
            if(uploadTypeStr.equals("line")) {
                 result = efBusinessService.delectTowerLine(id);
            }
            if(result>0){
                return ResultUtil.success("删除数据成功！");
            }
            return ResultUtil.error("删除数据失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("删除数据失败！");
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


    /**
     *
     * @param operationTypeStr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{operationType}/addOrupdateTower")
    public Result addOrupdateTower(@PathVariable("operationType") String operationTypeStr, @RequestBody EfTower efTower){
        try {
            if (operationTypeStr.equals("hand")) {
                efTower.setCreateTime(new Date());
                efTower.setUpdateTime(new Date());
               efTower= efBusinessService.insertOrUpdate(efTower);
                return ResultUtil.success("success",efTower);
            }else {
                return ResultUtil.error("操作类型错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }
    }

    /**
     *
     * @param operationTypeStr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{operationType}/addOrupdateLine")
    public Result addOrupdateLine(@PathVariable("operationType") String operationTypeStr, @RequestBody EfTowerLine efTowerLine){
        try {
            if (operationTypeStr.equals("hand")) {
                efTowerLine.setCreateTime(new Date());
                efTowerLine= efBusinessService.addOrupdateLine(efTowerLine);
                return ResultUtil.success("success",efTowerLine);
            }else {
                return ResultUtil.error("操作类型错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }
    }
    /**
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{operationType}/batchInsertTower")
    public Result batchInsertTower( @RequestParam("file") MultipartFile file){
        try {
            // 读取文件内容
            try (InputStream inputStream = file.getInputStream();
                 XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

                Sheet sheet = workbook.getSheetAt(0);
                // 在此处添加对文件数据的处理逻辑，例如遍历行和列获取数据
                // 获取第一行的第一列
                Cell cell1 = sheet.getRow(0).getCell(0);
                System.out.println("第一行第一列的值: " + cell1.getStringCellValue());
                List<EfTower> efTowerList = new ArrayList<>();
                // 遍历所有行，从第二行开始（索引为1，因为索引是从0开始的）
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) { // 确保行不为空
                        // 遍历每一行的所有单元格
                        EfTower efTower = new EfTower();
                        for (Cell cell : row) {
                            // 根据单元格的类型来获取数据
                            // 当索引为0时，
                            Integer columnIndex = cell.getColumnIndex();
                            switch (cell.getCellType()) {
                                case STRING:
                                    if (columnIndex == 0) {
                                        efTower.setMark(cell.getStringCellValue());
                                    }else if (columnIndex == 1) {
                                        efTower.setName(cell.getStringCellValue());
                                    }else if (columnIndex == 2) {
                                        efTower.setType(cell.getStringCellValue()); // 塔型
                                    }else if (columnIndex == 3) {
                                        efTower.setSpan(Double.parseDouble(cell.getStringCellValue())); // 档距
                                    }else if (columnIndex == 4) {
                                        efTower.setTerrain(cell.getStringCellValue()); // 地形
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(Double.parseDouble(cell.getStringCellValue())); // 海拔
                                    }else if (columnIndex == 6) {
                                        efTower.setLon(cell.getNumericCellValue()); //
                                    }else if (columnIndex == 7) {
                                        efTower.setLat(cell.getNumericCellValue()); // 7 纬度
                                    }else if (columnIndex == 8) {
                                        efTower.setGeo(cell.getStringCellValue()); // dix
                                    }else if (columnIndex == 9) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue()); // daoxxi
                                    }else if (columnIndex == 10) {
                                        efTower.setLineLineDis(cell.getNumericCellValue()); // xian
                                    }else if (columnIndex == 11) {
                                        efTower.setLineTowerDis(Double.parseDouble(cell.getStringCellValue())); // xianta
                                    }else if (columnIndex == 12) {
                                        efTower.setTowerRotationAngle(Double.parseDouble(cell.getStringCellValue()));
                                    }else if (columnIndex == 13) {
                                        efTower.setDes(cell.getStringCellValue());
                                    }
                                    break;
                                case NUMERIC:
                                    if(columnIndex == 0){}
                                    if (columnIndex == 0) {
                                        efTower.setMark(cell.getStringCellValue());
                                    }else if (columnIndex == 1) {
                                        efTower.setName(cell.getStringCellValue());
                                    }else if (columnIndex == 2) {
                                        efTower.setType(cell.getStringCellValue()); // 塔型
                                    }else if (columnIndex == 3) {
                                        efTower.setSpan(cell.getNumericCellValue()); // 档距
                                    }else if (columnIndex == 4) {
                                        efTower.setTerrain(String.valueOf(cell.getNumericCellValue())); // 地形
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(cell.getNumericCellValue()); // 海拔
                                    }else if (columnIndex == 6) {
                                        efTower.setLon(cell.getNumericCellValue()); //
                                    }else if (columnIndex == 7) {
                                        efTower.setLat(cell.getNumericCellValue());  // 7 纬度
                                    }else if (columnIndex == 8) {
                                        efTower.setGeo(String.valueOf(cell.getNumericCellValue()));
                                    }else if (columnIndex == 9) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue()); // daoxxi
                                    }else if (columnIndex == 10) {
                                        efTower.setLineLineDis(cell.getNumericCellValue()); // xian
                                    }else if (columnIndex == 11) {
                                        efTower.setLineTowerDis(cell.getNumericCellValue()); // xianta
                                    } else if (columnIndex == 12) {
                                        efTower.setTowerRotationAngle(cell.getNumericCellValue());
                                    }else if (columnIndex == 13) {
                                        efTower.setDes(String.valueOf(cell.getNumericCellValue()));
                                    }
                                    break;
                                default:
                                    System.out.print("Unknown cell type\t");
                            }
                        }
                        efTower.setCreateTime(new Date());
                        efTower.setUpdateTime(new Date());
                        efTowerList.add(efTower);
                        System.out.println(); // 换行
                    }
                }
                efBusinessService.batchInsertTower(efTowerList);
                return ResultUtil.success("文件数据读取成功",efTowerList);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error("文件读取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }

    }




    /**
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{operationType}/batchInsertToweLine")
    public Result batchInsertTower2( @RequestParam("file") MultipartFile file){
        try {
            // 读取文件内容
            try (InputStream inputStream = file.getInputStream();
                 XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

                Sheet sheet = workbook.getSheetAt(0);
                // 在此处添加对文件数据的处理逻辑，例如遍历行和列获取数据
                Cell cell1 = sheet.getRow(0).getCell(0);
                List<EfTowerLine> efTowerLines = new ArrayList<>();
                // 遍历所有行，从第二行开始（索引为1，因为索引是从0开始的）
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) { // 确保行不为空
                        // 遍历每一行的所有单元格
                        EfTowerLine efTowerLine = new EfTowerLine();
                        for (Cell cell : row) {
                            Integer columnIndex = cell.getColumnIndex();
                            switch (cell.getCellType()) {
                                case STRING:
                                    if (columnIndex == 0) {
                                        efTowerLine.setMark(cell.getStringCellValue());
                                    }else if (columnIndex == 1) {
                                        efTowerLine.setStartTowerMark(cell.getStringCellValue());
                                    }else if (columnIndex == 2) {
                                        efTowerLine.setEndTowerMark(cell.getStringCellValue());
                                    }else if (columnIndex == 3) {
                                        efTowerLine.setLineLength(Double.parseDouble(cell.getStringCellValue()));
                                            }else if (columnIndex == 4) {
                                        efTowerLine.setDes(cell.getStringCellValue());
                                    }
                                    break;
                                case NUMERIC:
                                    if(columnIndex == 0){}
                                    if (columnIndex == 0) {
                                        efTowerLine.setMark(String.valueOf(cell.getNumericCellValue()));
                                    }else if (columnIndex == 1) {
                                        efTowerLine.setStartTowerMark(String.valueOf(cell.getNumericCellValue()));
                                    }else if (columnIndex == 2) {
                                        efTowerLine.setEndTowerMark(String.valueOf(cell.getNumericCellValue()));
                                    }else if (columnIndex == 3) {
                                        efTowerLine.setLineLength(cell.getNumericCellValue());
                                    }else if (columnIndex == 4) {
                                        efTowerLine.setDes(String.valueOf(cell.getNumericCellValue()));
                                    }
                                    break;
                                default:
                                    System.out.print("Unknown cell type\t");
                            }
                        }
                        efTowerLine.setCreateTime(new Date());
                        efTowerLines.add(efTowerLine);
                    }
                }
                efBusinessService.batchInsertTowerLine(efTowerLines);
                return ResultUtil.success("文件数据读取成功",efTowerLines);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error("文件读取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传媒体失败！");
        }

    }




}
