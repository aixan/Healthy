package cn.aixan.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ResultUtils;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.service.SetmealService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aix QQ:32729842
 * @version 2022-09-09 15:40
 */
@RestController
@RequestMapping("/setMeal")
public class SetMealController {
    @DubboReference
    private SetmealService setmealService;

    @GetMapping
    public BaseResponse<Object> getSetMeal() {
        List<Setmeal> list = setmealService.list();
        return ResultUtils.successToData(list);
    }

    @GetMapping("/{id}")
    public BaseResponse<Object> getSetMealById(@PathVariable Long id) {
        Setmeal setmeal = setmealService.getSetMealByIdDetails(id);
        return ResultUtils.successToData(setmeal);
    }

}
