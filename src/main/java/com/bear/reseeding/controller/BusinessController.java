package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.EfPerilPoint;
import com.bear.reseeding.entity.EfTower;
import com.bear.reseeding.entity.EfUser;
import com.bear.reseeding.model.CurrentUser;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfBusinessService;
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
import java.util.ArrayList;
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
                                        efTower.setLat(cell.getNumericCellValue());
                                    }else if (columnIndex == 3) {
                                        efTower.setLon(cell.getNumericCellValue());
                                    }else if (columnIndex == 4) {
                                        efTower.setAlt(cell.getNumericCellValue());
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(cell.getNumericCellValue());
                                    }else if (columnIndex == 6) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue());
                                    }else if (columnIndex == 7) {
                                        efTower.setLineLineDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 8) {
                                        efTower.setLineTowerDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 9) {
                                        efTower.setTowerRotationAngle(cell.getNumericCellValue());
                                    }else if (columnIndex == 10) {
                                        efTower.setSpan(cell.getNumericCellValue());
                                    }else if (columnIndex == 11) {
                                        efTower.setTerrain(String.valueOf(cell.getStringCellValue()));
                                    }
                                    else if (columnIndex == 12) {
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
                                        efTower.setLat(cell.getNumericCellValue());
                                    }else if (columnIndex == 3) {
                                        efTower.setLon(cell.getNumericCellValue());
                                    }else if (columnIndex == 4) {
                                        efTower.setAlt(cell.getNumericCellValue());
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(cell.getNumericCellValue());
                                    }else if (columnIndex == 6) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue());
                                    }else if (columnIndex == 7) {
                                        efTower.setLineLineDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 8) {
                                        efTower.setLineTowerDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 9) {
                                        efTower.setTowerRotationAngle(cell.getNumericCellValue());
                                    }else if (columnIndex == 10) {
                                        efTower.setSpan(cell.getNumericCellValue());
                                    }else if (columnIndex == 11) {
                                        efTower.setTerrain(String.valueOf(cell.getStringCellValue()));
                                    }
                                    else if (columnIndex == 12) {
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
    @PostMapping(value = "/{operationType}/batchInsertTower2")
    public Result batchInsertTower2( @RequestParam("file") MultipartFile file){
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
                                        efTower.setLat(cell.getNumericCellValue());
                                    }else if (columnIndex == 3) {
                                        efTower.setLon(cell.getNumericCellValue());
                                            }else if (columnIndex == 4) {
                                            efTower.setAlt(cell.getNumericCellValue());
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(cell.getNumericCellValue());
                                    }else if (columnIndex == 6) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue());
                                    }else if (columnIndex == 7) {
                                        efTower.setLineLineDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 8) {
                                        efTower.setLineTowerDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 9) {
                                        efTower.setTowerRotationAngle(cell.getNumericCellValue());
                                    }else if (columnIndex == 10) {
                                        efTower.setSpan(cell.getNumericCellValue());
                                    }else if (columnIndex == 11) {
                                        efTower.setTerrain(String.valueOf(cell.getStringCellValue()));
                                    }
                                    else if (columnIndex == 12) {
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
                                        efTower.setLat(cell.getNumericCellValue());
                                    }else if (columnIndex == 3) {
                                        efTower.setLon(cell.getNumericCellValue());
                                    }else if (columnIndex == 4) {
                                        efTower.setAlt(cell.getNumericCellValue());
                                    }else if (columnIndex == 5) {
                                        efTower.setAbsalt(cell.getNumericCellValue());
                                    }else if (columnIndex == 6) {
                                        efTower.setVerticalLineArc(cell.getNumericCellValue());
                                    }else if (columnIndex == 7) {
                                        efTower.setLineLineDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 8) {
                                        efTower.setLineTowerDis(cell.getNumericCellValue());
                                    }else if (columnIndex == 9) {
                                        efTower.setTowerRotationAngle(cell.getNumericCellValue());
                                    }else if (columnIndex == 10) {
                                        efTower.setSpan(cell.getNumericCellValue());
                                    }else if (columnIndex == 11) {
                                        efTower.setTerrain(String.valueOf(cell.getStringCellValue()));
                                    }
                                    else if (columnIndex == 12) {
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




}
