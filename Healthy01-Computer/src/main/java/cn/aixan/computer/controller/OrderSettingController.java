package cn.aixan.computer.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Ordersetting;
import cn.aixan.model.vo.OrderSettingVo;
import cn.aixan.service.OrdersettingService;
import cn.aixan.util.FileUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author aix QQ:32729842
 * @version 2022-09-08 14:34
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Value("${aixan.upload.excel}")
    private String path;

    @DubboReference
    private OrdersettingService ordersettingService;

    @PostMapping("/list")
    public BaseResponse<Object> getMonthList(Date date) {
        List<OrderSettingVo> list = ordersettingService.getMonthList(date);
        return ResultUtils.successToData(list);
    }

    @PostMapping
    public BaseResponse<Object> updateDayNumber(@RequestBody Ordersetting ordersetting) {
        if (ordersetting == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        System.out.println(ordersetting);
        boolean save = ordersettingService.updateDayNumber(ordersetting);
        return ResultUtils.success(MessageConstant.EDIT_ORDER_SUCCESS);
    }

    @PostMapping("/upload")
    public BaseResponse<Object> upload(@RequestParam("excelFile") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        // 判断文件格式是否正确
        // 保存文件
        String fileName = FileUtils.newFileName(originalFilename);

        File file = new File(path, FileUtils.newFilePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        File saveFile = new File(file, fileName);
        multipartFile.transferTo(saveFile);

        // 把上传的excel转成list
        List<Ordersetting> list = excelToList(saveFile);
        ordersettingService.templateSave(list);
        return ResultUtils.success(MessageConstant.ADD_ORDER_SUCCESS);
    }

    public List<Ordersetting> excelToList(File file) {
        // 创建一个工作薄
        try (XSSFWorkbook workbook = new XSSFWorkbook(file);) {
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            // 获取上传的excel表中有多少行数据
            int number = sheet.getPhysicalNumberOfRows();
            List<Ordersetting> list = new ArrayList<>();
            for (int i = 1; i < number; i++) {
                // 获取行数据
                XSSFRow row = sheet.getRow(i);
                // 单元格
                Date date = row.getCell(0).getDateCellValue();
                double numeric = row.getCell(1).getNumericCellValue();
                int intValue = Double.valueOf(numeric).intValue();
                Ordersetting ordersetting = new Ordersetting();
                ordersetting.setNumber(intValue);
                ordersetting.setOrderdate(date);
                list.add(ordersetting);
            }
            return list;
        } catch (IOException | InvalidFormatException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传文件异常");
        }
    }

}
