package cn.aixan.controller;

import cn.aixan.model.domain.Setmeal;
import cn.aixan.service.SetmealService;
import cn.aixan.util.QueryPage;
import cn.aixan.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 套餐控制器
 * @author aix QQ:32729842
 * @version 2022-09-06 15:12
 */
@RestController
@RequestMapping("/setMeal")
public class SetMealController {
    @DubboReference
    public SetmealService setmealService;

    @PostMapping("/list")
    public Result<Object> getSetMealPage(@RequestBody QueryPage queryPage){
        if (queryPage == null) {
            queryPage = new QueryPage(1,10,"");
        }
        if (StringUtils.isNotBlank(queryPage.getQueryString())) {
            queryPage.setQueryString("");
        }
        Page<Setmeal> page = setmealService.getCheckGroupPage(queryPage);
        return Result.success(page);
    }
}
