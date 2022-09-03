package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Order;
import cn.aixan.service.OrderService;
import cn.aixan.mapper.OrderMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_order(订单)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




