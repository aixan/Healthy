package cn.aixan.service.impl;

import cn.aixan.common.ErrorCode;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.mapper.OrderMapper;
import cn.aixan.model.domain.Member;
import cn.aixan.model.domain.Order;
import cn.aixan.model.domain.Ordersetting;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.model.vo.OrderVo;
import cn.aixan.service.MemberService;
import cn.aixan.service.OrderService;
import cn.aixan.service.OrdersettingService;
import cn.aixan.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author aix
 * @description 针对表【t_order(订单)】的数据库操作Service实现
 * @createDate 2022-09-02 15:25:10
 */
@DubboService
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {

    @Resource
    private OrdersettingService ordersettingService;
    @Resource
    private MemberService memberService;
    @Resource
    private SetmealService setmealService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addOrder(OrderVo orderVo) throws ParseException {
        // 校验字段
        String orderDate = orderVo.getOrderDate();
        // 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行 预约
        QueryWrapper<Ordersetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderDate", orderDate);
        Ordersetting orderSetting = ordersettingService.getOne(queryWrapper);
        if (orderSetting == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        // 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        Integer reservations = orderSetting.getReservations();
        Integer number = orderSetting.getNumber();
        if (reservations >= number) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.ORDER_FULL);
        }
        // 4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
        String phone = orderVo.getPhone();
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("phoneNumber", phone);
        Member memberServiceOne = memberService.getOne(memberQueryWrapper);
        if (memberServiceOne == null) {
            // 添加用户
            memberServiceOne = new Member();
            memberServiceOne.setIdcard(orderVo.getIdCard());
            memberServiceOne.setName(orderVo.getName());
            memberServiceOne.setPhonenumber(orderVo.getPhone());
            memberServiceOne.setSex(orderVo.getSex() == 1 ? "男" : "女");
            memberServiceOne.setRegtime(new Date());
            memberServiceOne.setPassword("123456");
            memberServiceOne.setBirthday(getBirthdayFromIdCard(orderVo.getIdCard()));
            memberService.save(memberServiceOne);
        }
        // 获取套餐信息
        Integer setMealId = orderVo.getSetMealId();
        QueryWrapper<Setmeal> setMealQueryWrapper = new QueryWrapper<>();
        setMealQueryWrapper.eq("id",setMealId);
        Setmeal setmeal = setmealService.getById(setMealId);
        if (setmeal == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.SELECTED_SET_MEAL_CANNOT_ORDER);
        }
        // 检查套餐要求性别会员是否符合
        Integer dbSex = new Integer(setmeal.getSex());
        Integer sex = orderVo.getSex();
        if (dbSex > 0 && !Objects.equals(dbSex,sex)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.SELECTED_SEX_CANNOT_ORDER);
        }
        // 检查套餐要求年龄会员是否符合
        String[] split = setmeal.getAge().split("-");
        String idCard = orderVo.getIdCard();


        // 3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约 则无法完成再次预约
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("member_id", memberServiceOne.getId());
        orderQueryWrapper.eq("orderDate", orderDate);
        Order order = this.getOne(orderQueryWrapper);
        if (order != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.HAS_ORDERED);
        }
        // 5、预约成功，更新当日的已预约人数
        Order newOrder = new Order();
        newOrder.setMemberId(memberServiceOne.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        newOrder.setOrderdate(simpleDateFormat.parse(orderDate));
        newOrder.setOrdertype("微信公众号预约");
        newOrder.setOrderstatus("未到诊");
        newOrder.setSetmealId(orderVo.getSetMealId());
        this.save(newOrder);
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        ordersettingService.updateById(orderSetting);
        return newOrder.getId().longValue();
    }

    @Override
    public OrderVo getOrder(Long id) {
        Order order = this.getById(id);
        // 获取会员信息
        Integer memberId = order.getMemberId();
        Member member = memberService.getById(memberId);
        // 获取套餐信息
        Integer setMealId = order.getSetmealId();
        Setmeal setMeal = setmealService.getById(setMealId);
        // 创建回显对象
        OrderVo orderVo = new OrderVo();
        orderVo.setName(member.getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = order.getOrderdate();
        String date = simpleDateFormat.format(orderDate);
        orderVo.setOrderDate(date);
        orderVo.setSetMealName(setMeal.getName());
        return orderVo;
    }

    private Date getBirthdayFromIdCard(String idCard) {
        //530624196904034233
        String time = idCard.substring(6, 14);
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}




