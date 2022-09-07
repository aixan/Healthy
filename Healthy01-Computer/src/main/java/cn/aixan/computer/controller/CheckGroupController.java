package cn.aixan.computer.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Checkgroup;
import cn.aixan.service.CheckgroupService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping
    public BaseResponse<Object> getCheckGroup(){
        List<Checkgroup> list = checkgroupService.list();
        return ResultUtils.successToData(list);
    }

    @PostMapping("/list")
    public BaseResponse<Object> getCheckGroupPage(@RequestBody QueryPage queryPage) {
        if (queryPage == null) {
            queryPage = new QueryPage(1, 10, "");
        }
        if (!StringUtils.isNotBlank(queryPage.getQueryString())) {
            queryPage.setQueryString("");
        }
        Page<Checkgroup> page = checkgroupService.getCheckGroupPage(queryPage);
        return ResultUtils.successToData(page);
    }

    @PostMapping
    public BaseResponse<Object> addCheckGroup(@RequestBody Checkgroup checkgroup, Integer[] checkItemId) {
        if (checkgroup == null || checkItemId.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean addResult = checkgroupService.addCheckGroupRelationCheckItem(checkgroup, checkItemId);
        if (!addResult) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_CHECK_GROUP_FAIL);
        }
        return ResultUtils.success(MessageConstant.ADD_CHECK_ITEM_SUCCESS);
    }

    @PutMapping
    public BaseResponse<Object> putCheckGroup(@RequestBody Checkgroup checkgroup, Integer[] checkItemId) {
        if (checkgroup == null || checkgroup.getId() <= 0 || checkItemId.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean putResult = checkgroupService.putCheckGroupRelationCheckItem(checkgroup, checkItemId);
        if (!putResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.EDIT_CHECK_GROUP_FAIL);
        }
        return ResultUtils.success(MessageConstant.EDIT_CHECK_ITEM_SUCCESS);
    }

    @GetMapping("/{id}")
    public BaseResponse<Object> getCheckItemByGroupId(@PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        Checkgroup checkgroup = checkgroupService.getCheckGroupById(id);
        return ResultUtils.successToData(checkgroup);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> deleteCheckGroupById(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean deleteResult = checkgroupService.deleteById(id);
        if (!deleteResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_CHECK_GROUP_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_CHECK_GROUP_SUCCESS);
    }

    @DeleteMapping
    public BaseResponse<Object> deleteCheckGroupByIds(Integer[] ids) {
        if (ids.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean deleteResult = checkgroupService.removeByIds(Arrays.asList(ids));
        if (!deleteResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_CHECK_GROUP_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_CHECK_GROUP_SUCCESS);
    }
}
