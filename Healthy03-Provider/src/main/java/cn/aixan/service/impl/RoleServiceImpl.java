package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Role;
import cn.aixan.service.RoleService;
import cn.aixan.mapper.RoleMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_role(角色表)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




