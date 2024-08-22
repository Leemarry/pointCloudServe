package com.bear.reseeding.utils;

import com.bear.reseeding.entity.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            System.out.println("未找到第二个 '/'");
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
