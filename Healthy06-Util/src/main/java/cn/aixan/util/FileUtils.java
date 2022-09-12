package cn.aixan.util;

import org.apache.poi.util.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author aix QQ:32729842
 * @version 2022-09-08 14:42
 */
public class FileUtils {
    private FileUtils() {
    }

    /**
     * 获取不重名文件名
     * @param name 文件名字
     * @return uuid + 文件格式
     */
    public static String newFileName(String name) {
        // 获取文件格式
        int indexOf = name.lastIndexOf(".");
        String fileType = name.substring(indexOf);
        // 获取UUID随机字符串，保证文件不重名
        String uuid = UUID.randomUUID().toString();
        return uuid + fileType;
    }

    /**
     * 获取今天日期路径
     * @return yyyy/MM/dd
     */
    public static String newFilePath(){
        // 获取今天时间，文件按目录保存
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return now.format(dateTimeFormatter);
    }
}
