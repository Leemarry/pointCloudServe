package com.bear.reseeding.utils;

import com.bear.reseeding.entity.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringUtil {


    public static String substring1(String folder) {
        int firstSlashIndex = folder.indexOf("/");
        if (firstSlashIndex == -1) {
            System.out.println("未找到第一个 '/'");
            return folder;
        }
        // 找到第二个 '/' 的位置，从第一个 '/' 之后的位置开始搜索
        int secondSlashIndex = folder.indexOf("/", firstSlashIndex + 1);
        if (secondSlashIndex == -1) {
//            System.out.println("未找到第二个 '/'");
            secondSlashIndex = folder.length();
        }
        String newFolder = folder.substring(firstSlashIndex + 1, secondSlashIndex);
        String[] folderArr = newFolder.split("_");
        String folderName = "";
        if (folderArr.length > 0) {
            folderName = folderArr[folderArr.length - 1];
        }
        return folderName;
    }


    public static String  processUrl(String fullUrl ,String Endpoint,String EndpointExt){
        // 首先，去除URL的协议部分（http:// 或 https://）
        String urlWithoutProtocol = fullUrl.replaceFirst("^"+ Endpoint, "");
        // 使用/分割URL
        String[] parts = urlWithoutProtocol.split("/");

        // 构建EndpointExt，从startIndex开始到倒数第四个元素（因为要去掉最后三个）
        StringBuilder endpointExt = new StringBuilder();
        for (int i = 0; i < parts.length - 3; i++) {
            if (i > 1) {
                endpointExt.append("/");
            }
            endpointExt.append(parts[i]);
        }

        // 构建路径模板
        String pathTemplate = "/{z}/{x}/{y}.png";
        String url = EndpointExt +"/"+ endpointExt.toString() + pathTemplate;
        return  url;
    }

    // miniosource/efuav-ortho-img/pointcloud/zseimage_202408160833_001_B0041064/zseimage_202408160833_001_B004/map/22/3430801/1724263.png
    public static String  processUrl2(String fullUrl){
        // 首先，去除URL的协议部分（http:// 或 https://）
//        String urlWithoutProtocol = fullUrl.replaceFirst("^"+ Endpoint, "");
        // 使用/分割URL
//        String[] parts = urlWithoutProtocol.split("/");

        String[] parts = fullUrl.split("/");
        // 构建EndpointExt，从startIndex开始到倒数第四个元素（因为要去掉最后三个）
        StringBuilder endpointExt = new StringBuilder();
        for (int i = 0; i < parts.length - 3; i++) {
            if (i > 1) {
                endpointExt.append("/");
            }
            endpointExt.append(parts[i]);
        }

        // 构建路径模板
        String pathTemplate = "/{z}/{x}/{y}.png";
        String url = "/"+ endpointExt.toString() + pathTemplate;
        return  url;
    }



    public static String extractPointcloud(String input ,String subString) {
        // 使用正则表达式匹配"pointcloud/"后面的部分
        Pattern pattern = Pattern.compile(subString+"/([^/]+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return subString+"/" + matcher.group(1);
        }
        return null; // 如果没有匹配，返回null
    }



//    public static void readFile(String MD5, MultipartFile file) {
//        if(file == null || file.isEmpty()){
//            return;
//        }
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            String line;
//            int count = 0;
//            Location location = new Location();
//            while ((line = br.readLine())!= null) {
//                count++;
//                if(count == 6){
//                    location.setLatitude(Double.parseDouble(line));
//                }
//                if(count == 5){
//                    location.setLongitude(Double.parseDouble(line));
//                }
//            }
//            fileContentHolder.setFileContent(MD5,location);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
