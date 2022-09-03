package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.SetmealCheckgroup;
import cn.aixan.service.SetmealCheckgroupService;
import cn.aixan.mapper.SetmealCheckgroupMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_setmeal_checkgroup(套餐与检查组，一个套餐有多个检查组)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class SetmealCheckgroupServiceImpl extends ServiceImpl<SetmealCheckgroupMapper, SetmealCheckgroup>
    implements SetmealCheckgroupService{

}




