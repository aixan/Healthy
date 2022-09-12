package cn.aixan.service;

import cn.aixan.common.BaseResponse;
import cn.aixan.model.domain.Order;
import cn.aixan.model.vo.OrderVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;

/**
* @author aix
* @description 针对表【t_order(订单)】的数据库操作Service
* @createDate 2022-09-02 15:25:10
*/
public interface OrderService extends IService<Order> {

    /**
     * 添加订单
     * @param orderVo 订单信息
     * @return 订单ID
     */
    Long addOrder(OrderVo orderVo) throws ParseException;

    /**
     * 根据ID获取订单详情
     * @param id 订单ID
     * @return 订单详情
     */
    OrderVo getOrder(Long id);
}
