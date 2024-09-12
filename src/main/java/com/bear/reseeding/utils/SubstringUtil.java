package com.bear.reseeding.utils;

import com.bear.reseeding.entity.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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


    public static List<String> verifyFormat(String folder){
        // String folder = "/B002_B009/user/Documents/Java";
        List<String> strings= new ArrayList<String>();
        // 尝试分割文件夹路径
        String[] folderArr = folder.split("/");
        String[] folderArr2 = folder.split("/");

        String parentFolder = folderArr2[0];
        // parentFolder 为空字符串 folderArr2.length>2 取 folderArr2[1]
        if (parentFolder.isEmpty() && folderArr2.length > 1) {
            parentFolder = folderArr2[1];
        }
        // 检查是否有足够的部分
//        if (folderArr.length <= 1 || folderArr[1].isEmpty()) {
//            System.out.println("路径格式不正确，无法找到有效的文件夹部分");
//            return null; // 或抛出异常
//        }
        strings.add(parentFolder);
        // 尝试使用下划线分割父文件夹
        String[] parts = parentFolder.split("_");
        // 检查分割后是否至少有两个部分
        if (parts.length < 2) {
            System.out.println("父文件夹部分格式不正确，无法找到足够的下划线分隔部分");
            return strings; // 或抛出异常
        }

        // 获取最后两个部分
        String part1 = parts[parts.length - 2];
        String part2 = parts[parts.length - 1];

        // 检查这两个部分是否都以A或B开头后跟数字
        if (!part1.matches("^[AB]\\d+") || !part2.matches("^[AB]\\d+")) {
            System.out.println("不是所有部分都以A或B开头后跟数字的格式");
            return strings; // 或抛出异常
        }

        // 如果所有检查都通过，则进行后续处理
        char prefix1 = part1.charAt(0);

        char prefix2 = part2.charAt(0);
        // 注意：通常情况下，prefix1 和 prefix2 应该是相同的，但这里仍然分别处理
        //System.out.println("第一个部分的开头字母与数字: " + prefix1 + part1.substring(1));
        //System.out.println("第二个部分的开头字母与数字: " + prefix2 + part2.substring(1));
        // 返回 prefix1   part1.substring(1)  part2.substring(1)
        strings.add(String.valueOf(prefix1));
        strings.add(String.valueOf(part1.substring(1)));
        strings.add(String.valueOf(part2.substring(1)));
        return strings;
    }



    public static String substring2(String folder) {
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
        return newFolder;
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
        // 获取倒数第三个/
        int lastindex = fullUrl.lastIndexOf("/");
        int lastindex2 = fullUrl.substring(0,lastindex).lastIndexOf("/");
        int lastindex3 = fullUrl.substring(0,lastindex2).lastIndexOf("/");

        String url =  fullUrl.substring(0,lastindex3);
        // 构建路径模板
        String pathTemplate = "/{z}/{x}/{y}.png";
        url = url + pathTemplate;
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
