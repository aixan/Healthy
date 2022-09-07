package cn.aixan.computer.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Checkitem;
import cn.aixan.service.CheckitemService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 检查项控制器
 *
 * @author aix QQ:32729842
 * @version 2022-09-02 16:20
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    Logger logger = LoggerFactory.getLogger(CheckItemController.class);
    @DubboReference
    private CheckitemService checkitemService;

    @GetMapping
    public BaseResponse<Object> list() {
        return ResultUtils.successToData(checkitemService.list());
    }

    @PostMapping
    public BaseResponse<Object> addCheckItem(@RequestBody Checkitem checkItem) {
        if (checkItem == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        if (StringUtils.isAnyBlank(checkItem.getName())) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean status = checkitemService.addCheckItem(checkItem);
        if (!status) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_CHECK_ITEM_FAIL);
        }
        return ResultUtils.success(MessageConstant.ADD_CHECK_ITEM_SUCCESS);
    }

    @PutMapping
    public BaseResponse<Object> putCheckItem(@RequestBody Checkitem checkItem) {
        if (checkItem == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        if (checkItem.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean status = checkitemService.putCheckItem(checkItem);
        if (!status) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.EDIT_CHECK_ITEM_FAIL);
        }
        return ResultUtils.success(MessageConstant.EDIT_CHECK_ITEM_SUCCESS);
    }

    /**
     * todo 是否有必要改get
     *
     * @param queryPage 分页查询
     * @return 分页数据
     */
    @PostMapping("/list")
    public BaseResponse<Object> listPage(@RequestBody QueryPage queryPage) {
        Page<Checkitem> checkItemPage = checkitemService.listPage(queryPage);
        return ResultUtils.successToData(checkItemPage);
    }

    /**
     * 根据ID删除数据
     *
     * @param id 检查项id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public BaseResponse<Object> deleteById(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean result = checkitemService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_CHECK_ITEM_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_CHECK_ITEM_SUCCESS);
    }

    /**
     * 根据ID数组批量删除数据
     *
     * @param ids 批量删除id数组
     * @return 删除结果
     */
    @DeleteMapping
    public BaseResponse<Object> deleteByIdS(Integer[] ids) {
        if (ids.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean result = checkitemService.removeByIds(Arrays.asList(ids));
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.DELETE_CHECK_ITEM_FAIL);
        }
        return ResultUtils.success(MessageConstant.DELETE_CHECK_ITEM_SUCCESS);
    }
}
