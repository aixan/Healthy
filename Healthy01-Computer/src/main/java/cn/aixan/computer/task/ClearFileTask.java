package cn.aixan.computer.task;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author aix QQ:32729842
 * @version 2022-09-07 22:22
 */
@Controller
public class ClearFileTask {
    @Value("${aixan.filePath}")
    private String filePath;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "* 55-59 22 7 * ?")
    public void clear() {
        LocalDate localDate = LocalDate.now();
        LocalDate theDayBefore = localDate.plusDays(-1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 获取前一天日期字符串
        String date = theDayBefore.format(dateTimeFormatter);
        Set<String> difference = stringRedisTemplate.opsForSet()
                .difference("upload_pic:" + date, "db_pic:" + date);
        difference.forEach(fileName -> {
            File file = new File(filePath, fileName);
            if (!file.delete()) {
                // todo 记录删除失败日志
            }
            // 删除成功
        });
        // 删除对应Redis的Key
        // stringRedisTemplate.delete("upload_pic:" + date);
        // stringRedisTemplate.delete("db_pic:" + date);
    }

}
