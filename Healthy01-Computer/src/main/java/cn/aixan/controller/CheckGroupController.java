package cn.aixan.controller;

import cn.aixan.constant.MessageConstant;
import cn.aixan.model.domain.Checkgroup;
import cn.aixan.service.CheckgroupService;
import cn.aixan.util.QueryPage;
import cn.aixan.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 检查组控制器
 *
 * @author aix QQ:32729842
 * @version 2022-09-05 14:07
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @DubboReference
    private CheckgroupService checkgroupService;

    @PostMapping("/list")
    public Result<Object> getCheckGroupPage(@RequestBody QueryPage queryPage) {
        if (queryPage == null) {
            queryPage = new QueryPage(1, 10, "");
        }
        if (!StringUtils.isNotBlank(queryPage.getQueryString())) {
            queryPage.setQueryString("");
        }
        Page<Checkgroup> page = checkgroupService.getCheckGroupPage(queryPage);
        return Result.success(page);
    }

    @PostMapping
    public Result<Object> addCheckGroup(@RequestBody Checkgroup checkgroup,Integer[] checkItemId){
        if (checkgroup == null || checkItemId.length == 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean addResult = checkgroupService.addCheckGroupRelationCheckItem(checkgroup,checkItemId);
        if (!addResult) {
            return Result.failed(MessageConstant.ADD_CHECK_GROUP_FAIL);
        }
        return Result.success(MessageConstant.ADD_CHECK_ITEM_SUCCESS);
    }

    @PutMapping
    public Result<Object> putCheckGroup(@RequestBody Checkgroup checkgroup,Integer[] checkItemId){
        if (checkgroup == null || checkgroup.getId() <= 0 || checkItemId.length == 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean putResult = checkgroupService.putCheckGroupRelationCheckItem(checkgroup,checkItemId);
        if (!putResult) {
            return Result.failed(MessageConstant.EDIT_CHECK_GROUP_FAIL);
        }
        return Result.success(MessageConstant.EDIT_CHECK_ITEM_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result<Object> deleteCheckGroupById(@PathVariable("id") Long id) {
        if (id <= 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean deleteResult = checkgroupService.deleteById(id);
        if (!deleteResult) {
            return Result.failed(MessageConstant.DELETE_CHECK_GROUP_FAIL);
        }
        return Result.success(MessageConstant.DELETE_CHECK_GROUP_SUCCESS);
    }

    @DeleteMapping
    public Result<Object> deleteCheckGroupByIds(Integer[] ids) {
        if (ids.length == 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean deleteResult = checkgroupService.removeByIds(Arrays.asList(ids));
        if (!deleteResult) {
            return Result.failed(MessageConstant.DELETE_CHECK_GROUP_FAIL);
        }
        return Result.success(MessageConstant.DELETE_CHECK_GROUP_SUCCESS);
    }
}
