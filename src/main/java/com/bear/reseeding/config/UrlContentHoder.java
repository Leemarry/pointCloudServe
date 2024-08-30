package com.bear.reseeding.config;

import com.bear.reseeding.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;




@Component
public class UrlContentHoder {
    private Map<String, String> fileContents = new ConcurrentHashMap<>();

    public void setFileContent(String fileName, String url) {
        //
        String isFileExists = getFileContent(fileName);
        if (isFileExists != null) {
           return;
        }
        fileContents.put(fileName, url);
    }

    public String getFileContent(String fileName) {
        return fileContents.get(fileName);
    }
    // 删除 fileName 对应的内容
    public void removeFileContent(String fileName) {
        fileContents.remove(fileName);
    }


    // 清除内容，如果需要的话
    public void clear() {
        fileContents.clear();
    }

}

