package cn.aixan.service;

import cn.aixan.exception.BusinessException;
import cn.aixan.model.domain.Ordersetting;
import cn.aixan.model.vo.OrderSettingVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @author aix
* @description 针对表【t_ordersetting(订单设置)】的数据库操作Service
* @createDate 2022-09-02 15:25:10
*/
public interface OrdersettingService extends IService<Ordersetting>{

    /**
     * 模板导入预约人数
     * @param list 预约信息
     * @return 导入结果
     */
    @Transactional(rollbackFor = Exception.class)
    boolean templateSave(List<Ordersetting> list) throws BusinessException;

    /**
     * 查询时间查询当月预约信息
     * @param date 时间
     * @return 当月预约信息集合
     */
    List<OrderSettingVo> getMonthList(Date date);

    /**
     * 编辑可预约人数
     * @param ordersetting 信息
     * @return 是否编辑成功
     */
    boolean updateDayNumber(Ordersetting ordersetting);
}
