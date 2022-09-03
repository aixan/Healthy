package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.CheckgroupCheckitem;
import cn.aixan.service.CheckgroupCheckitemService;
import cn.aixan.mapper.CheckgroupCheckitemMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_checkgroup_checkitem(栓查项与检查组的中间表，一个检查组有多个检查项)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:09
*/
@DubboService
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem>
    implements CheckgroupCheckitemService{

}




