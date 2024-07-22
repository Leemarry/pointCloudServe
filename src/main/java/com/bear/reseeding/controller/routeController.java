package com.bear.reseeding.controller;

import com.bear.reseeding.common.ResultUtil;
import com.bear.reseeding.entity.EfTaskKmz;
import com.bear.reseeding.entity.EfUser;
import com.bear.reseeding.entity.Meta;
import com.bear.reseeding.model.CurrentUser;
import com.bear.reseeding.model.Result;
import com.bear.reseeding.service.RouteService;
import com.bear.reseeding.task.MinioService;
import com.bear.reseeding.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("route")
public class routeController {

    @Value("${minio.BucketNameKmz}")
    private String BucketNameKmz;
    @Value("${spring.application.name}")
    private String applicationName;
    @Resource
    private RouteService routeService;
    /**
     * minio
     */
    @Resource
    private MinioService minioService;
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


    /**
     *  获取kmz航线数据信息
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/kmz/querylist")
public Result getPhotoList(@RequestParam(value = "offset", defaultValue = "0") int offset,@RequestParam(value = "limit", defaultValue = "10") int limit){
        try{
            List<EfTaskKmz> list = routeService.queryAllByLimit( offset,  limit); //获取所有kmz数据
            return ResultUtil.success("获取成功",list);
        }catch (Exception e){
            return ResultUtil.error(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/kmz/upload")
    public  Result upload(@CurrentUser EfUser currentUser, @RequestPart ("files")  MultipartFile[] files, @RequestPart("efTaskKmzs") @Validated List<EfTaskKmz> efTaskKmzs){
        try{
            if(files == null || files.length == 0){
                return ResultUtil.error("请选择文件");
            }
            Integer ucId = currentUser.getId();
            for(MultipartFile file : files){
              // 文件名
              String fileName = file.getOriginalFilename();
              // 文件大小
              long fileSize = file.getSize();
              // 文件类型 通过文件后缀截取
              String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
              // 文件流
              byte[] fileBytes = file.getBytes();
              InputStream inputStream = file.getInputStream();
              // 通过文件名fileName 在 efTaskKmzs中找到对应的EfTaskKmz对象
              for(EfTaskKmz efTaskKmz : efTaskKmzs){
                  if(fileName.equals(efTaskKmz.getKmzName())) {
                      efTaskKmz.setKmzSize(fileSize);
                      efTaskKmz.setKmzType(fileType);
                      // 上传minion
                      String url = applicationName + "/kmzTasks/" + ucId + "/" + fileName;
                      if (!minioService.uploadfile("kmz", url, "kmz",inputStream)) {
                          return ResultUtil.error("保存巡检航线失败(/生成kmzminio有误)！"); //生成kmzminio有误
                      }
                      url = minioService.getPresignedObjectUrl(BucketNameKmz, url);
                      if ("".equals(url)) {
                          return ResultUtil.error("保存巡检航线失败(错误码 4)！");
                      }
                      efTaskKmz.setKmzPath(url);
                  }
                      routeService.saveKmz(efTaskKmz);
                  // 将 efTaskKmz 从数组移除 防止重复上传
                  efTaskKmzs.remove(efTaskKmz);
                      break;
              }
            }
            return ResultUtil.success("上传成功");
        }catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/updateGoods")
    @ResponseBody
    public Result updateGoods(
            @RequestPart("meta")  List<Meta> meta,
            @RequestPart("file")  MultipartFile file) {
        System.out.println("meta"+meta);
        return ResultUtil.success();
    }

//    @ResponseBody
//    @PostMapping(value = "/kmz/upload")
//    public  Result uploads(@CurrentUser EfUser currentUser, @RequestParam("files")  MultipartFile[] files,@RequestParam  Map<String,String> params){
//        try{
//            if(files == null || files.length == 0){
//                return ResultUtil.error("请选择文件");
//            }
////            return routeService.upload(files);
//            return ResultUtil.success();
//        }catch (Exception e) {
//            return ResultUtil.error(e.getMessage());
//        }
//    }




}
