package cn.aixan.controller;

import cn.aixan.constant.MessageConstant;
import cn.aixan.model.domain.Checkitem;
import cn.aixan.service.CheckitemService;
import cn.aixan.util.QueryPage;
import cn.aixan.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 检查项控制器
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
    public List<Checkitem> list(){
        return checkitemService.list();
    }

    @PostMapping
    public Result<Object> addCheckItem(@RequestBody Checkitem checkItem){
        if (checkItem == null) {
            return Result.failed(MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        if (StringUtils.isAnyBlank(checkItem.getName())) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean status = checkitemService.addCheckItem(checkItem);
        if (!status) {
            return Result.failed(MessageConstant.ADD_CHECK_ITEM_FAIL);
        }
        return Result.success(MessageConstant.ADD_CHECK_ITEM_SUCCESS);
    }

    @PutMapping
    public Result<Object> putCheckItem(@RequestBody Checkitem checkItem){
        if (checkItem == null) {
            return Result.failed(MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        if (checkItem.getId() <= 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean status = checkitemService.putCheckItem(checkItem);
        if (!status) {
            return Result.failed(MessageConstant.EDIT_CHECK_ITEM_FAIL);
        }
        return Result.success(MessageConstant.EDIT_CHECK_ITEM_SUCCESS);
    }

    /**
     * todo 是否有必要改get
     * @param queryPage 分页查询
     * @return 分页数据
     */
    @PostMapping("/list")
    public Result<Object> listPage(@RequestBody(required = false) QueryPage queryPage) {
        Page<Checkitem> checkItemPage = checkitemService.listPage(queryPage);
        return Result.success(checkItemPage);
    }

    /**
     * 根据ID删除数据
     * @param id 检查项id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Object> deleteById(@PathVariable("id") Long id) {
        if (id <= 0) {
            return Result.failed(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        boolean result = checkitemService.removeById(id);
        if (!result) {
            return Result.failed(MessageConstant.DELETE_CHECK_ITEM_FAIL);
        }
        return Result.success(MessageConstant.DELETE_CHECK_ITEM_SUCCESS);
    }
}
