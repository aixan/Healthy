package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Checkgroup;
import cn.aixan.service.CheckgroupService;
import cn.aixan.mapper.CheckgroupMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_checkgroup(检查组)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:09
*/
@DubboService
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup>
    implements CheckgroupService{

}




