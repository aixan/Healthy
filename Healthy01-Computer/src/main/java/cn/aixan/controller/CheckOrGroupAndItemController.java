package cn.aixan.controller;

import cn.aixan.constant.MessageConstant;
import cn.aixan.model.domain.Checkitem;
import cn.aixan.service.CheckgroupCheckitemService;
import cn.aixan.util.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组和检查项关联控制器
 * @author aix QQ:32729842
 * @version 2022-09-05 16:35
 */
@RestController
@RequestMapping("/check_group_item")
public class CheckOrGroupAndItemController {
    @DubboReference
    private CheckgroupCheckitemService checkgroupCheckitemService;

    @GetMapping("/{id}")
    public Result<Object> getCheckItemByGroupId(@PathVariable("id") Integer id) {
        if (id <= 0) {
            return Result.failed(MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        List<Integer> list = checkgroupCheckitemService.getCheckItemByGroupId(id);
        return Result.success(list);
    }
}
