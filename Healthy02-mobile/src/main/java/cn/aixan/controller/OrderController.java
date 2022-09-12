package cn.aixan.controller;

import cn.aixan.common.BaseResponse;
import cn.aixan.common.ErrorCode;
import cn.aixan.common.ResultUtils;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Order;
import cn.aixan.model.vo.OrderVo;
import cn.aixan.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author aix QQ:32729842
 * @version 2022-09-09 19:35
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @DubboReference
    private OrderService orderService;

    @PostMapping
    public BaseResponse<Object> addOrder(@RequestBody OrderVo orderVo) {
        Long id = null;
        try {
            id = orderService.addOrder(orderVo);
        } catch (ParseException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_ORDER_FAIL);
        }
        return ResultUtils.successToData(id);
    }

    @GetMapping("/{id}")
    public BaseResponse<Object> getOrder(@PathVariable Long id) {
        OrderVo order = orderService.getOrder(id);
        return ResultUtils.successToData(order);
    }

}
