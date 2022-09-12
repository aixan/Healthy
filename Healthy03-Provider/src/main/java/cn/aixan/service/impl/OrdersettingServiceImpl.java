package cn.aixan.service.impl;

import cn.aixan.common.ErrorCode;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.mapper.OrdersettingMapper;
import cn.aixan.model.domain.Ordersetting;
import cn.aixan.model.vo.OrderSettingVo;
import cn.aixan.service.OrdersettingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author aix
 * @description 针对表【t_ordersetting(订单设置)】的数据库操作Service实现
 * @createDate 2022-09-02 15:25:10
 */
@DubboService
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting>
        implements OrdersettingService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean templateSave(List<Ordersetting> list) throws BusinessException {
        if (list.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.ADD_ORDER_FAIL);
        }
        for (Ordersetting orderSetting : list) {
            // 判断当前日期是否存在条件
            QueryWrapper<Ordersetting> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("orderDate", orderSetting.getOrderdate());
            // 数据库查询
            Ordersetting orderSettingDb = this.getOne(queryWrapper);
            // 存在操作
            if (orderSettingDb != null) {
                Integer reservations = orderSettingDb.getReservations();
                Integer number = orderSetting.getNumber();
                Integer dbNumber = orderSettingDb.getNumber();
                if (!Objects.equals(number,dbNumber) && number > reservations) {
                    // 修改人数大于预约人数
                    orderSettingDb.setNumber(number);
                    // 预约人数设置为空，防止修改时有人预约
                    orderSettingDb.setReservations(null);
                    boolean b = this.updateById(orderSettingDb);
                    if (!b) {
                        throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_ORDER_FAIL);
                    }
                } else if (number < reservations) {
                    // 预约人数大于修改人数
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "预约人数大于修改人数");
                }
            } else {
                // 不存在操作
                boolean save = this.save(orderSetting);
                if (!save) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_ORDER_FAIL);
                }
            }
        }
        return true;
    }

    @Override
    public List<OrderSettingVo> getMonthList(Date date) {
        QueryWrapper<Ordersetting> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String format = dateFormat.format(date);
        queryWrapper.likeRight("orderDate",format);
        List<Ordersetting> list = this.list(queryWrapper);
        return list.stream().map(ordersetting -> {
            OrderSettingVo orderSettingVo = new OrderSettingVo();
            int day = ordersetting.getOrderdate().getDate();
            orderSettingVo.setDate(day);
            orderSettingVo.setNumber(ordersetting.getNumber());
            orderSettingVo.setReservations(ordersetting.getReservations());
            return orderSettingVo;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean updateDayNumber(Ordersetting ordersetting) {
        if (ordersetting == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(ordersetting.getOrderdate());
        QueryWrapper<Ordersetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderDate",date);
        Ordersetting orderSettingDb = this.getOne(queryWrapper);
        if (orderSettingDb != null) {
            Integer reservationsDb = orderSettingDb.getReservations();
            Integer number = ordersetting.getNumber();
            Integer dbNumber = orderSettingDb.getNumber();
            if (!Objects.equals(number,dbNumber) && number > reservationsDb) {
                orderSettingDb.setNumber(number);
                // 预约人数设置为空，防止修改时有人预约
                orderSettingDb.setReservations(null);
                boolean b = this.updateById(orderSettingDb);
                if (!b) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_ORDER_FAIL);
                }
            } else if (number < reservationsDb) {
                // 预约人数大于修改人数
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "预约人数大于修改人数");
            }
        } else {
            ordersetting.setReservations(null);
            boolean save = this.save(ordersetting);
        }
        return false;
    }
}




