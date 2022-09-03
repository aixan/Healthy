package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.UserRole;
import cn.aixan.service.UserRoleService;
import cn.aixan.mapper.UserRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_user_role(员工表与角色表的中间表，一个员工有多个角色)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




