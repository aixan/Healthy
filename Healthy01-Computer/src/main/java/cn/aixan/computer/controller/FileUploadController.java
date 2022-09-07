package cn.aixan.computer.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件上传类
 *
 * @author aix QQ:32729842
 * @version 2022-09-07 16:59
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Value("${aixan.filePath}")
    private String filePath;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/upload")
    public BaseResponse<Object> doUpload(@RequestParam("imgFile") MultipartFile multipartFile) throws IOException {
        // todo 文件类型限制
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isAnyBlank(originalFilename)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }

        String fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuidToString = UUID.randomUUID().toString();

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = localDate.format(dateTimeFormatter);


        File file = new File(filePath, date);
        if (!file.exists()) {
            file.mkdirs();
        }

        fileName = uuidToString + fileName;
        multipartFile.transferTo(new File(file, fileName));

        fileName = date + "/" + fileName;
        stringRedisTemplate.opsForSet().add("upload_pic:" + date, fileName);
        return ResultUtils.successToData(fileName);
    }

}
