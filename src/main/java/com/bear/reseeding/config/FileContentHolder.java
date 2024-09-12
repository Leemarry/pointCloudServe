package com.bear.reseeding.config;

import com.bear.reseeding.entity.Location;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class FileContentHolder {
    private Map<String, Location> fileContents = new ConcurrentHashMap<>();

    public void setFileContent(String fileName, Location location) {
        fileContents.put(fileName, location);
    }

    public Location getFileContent(String fileName) {
        return fileContents.get(fileName);
    }

    public void deleteFileContent(String fileName) {
        fileContents.remove(fileName);
    }


    // 清除内容，如果需要的话
    public void clear() {
        fileContents.clear();
    }

    // 假设你还需要一个方法来添加多个位置点，可能以文件名和位置列表的形式
    public void setFileContents(String fileName, List<Location> locations) {
        // 这里需要根据业务逻辑处理，比如只保留最后一个位置点
        // 或者你可能需要一个不同的数据结构来存储多个位置点
        // 下面的代码示例仅保留最后一个位置点
        if (!locations.isEmpty()) {
            setFileContent(fileName, locations.get(locations.size() - 1));
        }
    }
}

