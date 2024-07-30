package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.EfPhoto;
import com.bear.reseeding.entity.EfMediaType;
import com.bear.reseeding.entity.EfPointCloud;
import com.bear.reseeding.entity.EfVideo;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.EfMediaPhotoService;
import com.bear.reseeding.service.EfMediaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@RestController
@RequestMapping("media")
public class MediaController {

    @Resource
    private EfMediaPhotoService efMediaPhotoService;

    @Resource
    private EfMediaService efMediaService;
    //TODO:

    @ResponseBody
    @PostMapping(value = "/{mediaType}/querylist")
    public Result getMediaList(@PathVariable("mediaType") String mediaTypeStr,
                               @RequestParam(value = "startTime", required = false) Date startTime,
                               @RequestParam(value = "endTime", required = false) Date endTime,
                               @RequestParam(value = "mark", required = false) String mark) {
        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
            mark = null;
        }
        try {
            // 15s 模仿网络延迟
//            Thread.sleep(15000);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeStr = sdf.format(startTime);
            String endTimeStr = sdf.format(endTime);
            EfMediaType mediaType = EfMediaType.valueOf(mediaTypeStr.toUpperCase());
            // 直接调用枚举中的方法获取媒体列表
            Object mediaList = mediaType.getMediaList(efMediaService, startTimeStr, endTimeStr, mark);
            return ResultUtil.success("success", mediaList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取媒体列表失败！");
        }
    }






    @ResponseBody
    @PostMapping(value = "/download")
    public Result getDownload(){

        try {
//            String kmzUrl = "http://127.0.0.1:9090/ceshi/test.zip";
//            byte[] kmzData = downloadFile(kmzUrl);
            String videoUrl = "http://vjs.zencdn.net/v/oceans.mp4";
            URL url = new URL(videoUrl);
            // 读取远程资源
            InputStream inputStream = url.openStream();
            InputStreamResource resource = new InputStreamResource(inputStream);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"oceans.mp4\"");
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");

//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(MediaType.parseMediaType("video/mp4"))
//                    .body(resource);

            return ResultUtil.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取杆塔列表失败！");
        }
    }




    private static byte[] downloadFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream(); //
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }



}


//    /**
//     * 获取媒体库照片列表 根据传递的数据进行相应的查询
//     * @param startTime
//     * @param endTime
//     * @param mark  模糊查询
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "/photo/querylist")
//    public Result getPhotoList(@RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "mark", required = false) String mark){
//        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
//            mark = null;
//        }
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String startTimeStr = sdf.format(startTime);
//            String endTimeStr = sdf.format(endTime);
//            List<EfPhoto> photoList=  efMediaService.getPhotolist(startTimeStr, endTimeStr, mark);// 返回结果 efMediaService
//            return ResultUtil.success("success",photoList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.error("获取杆塔列表失败！");
//        }
//
//    }
//
//    /**
//     * 获取媒体库视频列表 根据传递的数据进行相应的查询
//     * @param startTime
//     * @param endTime
//     * @param mark
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "/video/querylist")
//    public Result getVideoList(@RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "mark", required = false) String mark){
//        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
//            mark = null;
//        }
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String startTimeStr = sdf.format(startTime);
//            String endTimeStr = sdf.format(endTime);
//            List<EfVideo> photoList=  efMediaService.getVideolist(startTimeStr, endTimeStr, mark);// 返回结果 efMediaService
//            return ResultUtil.success("success",photoList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.error("获取杆塔列表失败！");
//        }
//    }
//
//
//
//    /**
//     * 获取媒体库视频列表 根据传递的数据进行相应的查询
//     * @param startTime
//     * @param endTime
//     * @param mark
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "/cloud/querylist")
//    public Result getCloudList(@RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "mark", required = false) String mark){
//        if (mark == null || mark.isEmpty() || mark.equals("undefined") || mark.equals("null")) {
//            mark = null;
//        }
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String startTimeStr = sdf.format(startTime);
//            String endTimeStr = sdf.format(endTime);
//            List<EfPointCloud> efPointClouds=  efMediaService.getCloudlist(startTimeStr, endTimeStr, mark);// 返回结果 efMediaService
//            return ResultUtil.success("success",efPointClouds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.error("获取杆塔列表失败！");
//        }
//    }


//    orthoImg