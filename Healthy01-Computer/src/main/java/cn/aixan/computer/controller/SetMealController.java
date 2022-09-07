package cn.aixan.computer.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.service.SetmealService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 套餐控制器
 *
 * @author aix QQ:32729842
 * @version 2022-09-06 15:12
 */
@RestController
@RequestMapping("/setMeal")
public class SetMealController {
    @DubboReference
    public SetmealService setmealService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/list")
    public BaseResponse<Object> getSetMealPage(@RequestBody QueryPage queryPage) {
        if (queryPage == null) {
            queryPage = new QueryPage(1, 10, "");
        }
        if (StringUtils.isNotBlank(queryPage.getQueryString())) {
            queryPage.setQueryString("");
        }
        Page<Setmeal> page = setmealService.getCheckGroupPage(queryPage);
        return ResultUtils.successToData(page);
    }

    @PostMapping
    public BaseResponse<Object> addSetMeal(@RequestBody Setmeal setmeal) {
        if (setmeal == null || setmeal.getCheckGroupIds().isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean status = setmealService.addSetMeal(setmeal);
        if (!status) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_SETMEAL_FAIL);
        }
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = localDate.format(dateTimeFormatter);
        stringRedisTemplate.opsForSet().add("db_pic:" + date, setmeal.getImg());
        return ResultUtils.success(MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @GetMapping("{id}")
    public BaseResponse<Object> getSetMeal(@PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        Setmeal setmeal = setmealService.getSetMealById(id);
        return ResultUtils.successToData(setmeal);
    }

    @DeleteMapping("{id}")
    public BaseResponse<Object> deleteSetMealById(@PathVariable("id") Integer id) {
        if (id == null || id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        boolean deleteResult = setmealService.removeById(id);
        if (!deleteResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_SET_MEAL_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_SET_MEAL_SUCCESS);
    }

    @DeleteMapping
    public BaseResponse<Object> deleteSetMealById(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        boolean deleteResult = setmealService.removeByIds(Arrays.asList(ids));
        if (!deleteResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_SET_MEAL_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_SET_MEAL_SUCCESS);
    }
}
