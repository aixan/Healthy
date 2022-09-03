package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Ordersetting;
import cn.aixan.service.OrdersettingService;
import cn.aixan.mapper.OrdersettingMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_ordersetting(订单设置)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting>
    implements OrdersettingService{

}




