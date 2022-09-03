package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.service.SetmealService;
import cn.aixan.mapper.SetmealMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{

}




