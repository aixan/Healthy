package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.RolePermission;
import cn.aixan.service.RolePermissionService;
import cn.aixan.mapper.RolePermissionMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_role_permission(角色与权限的中间表，一个角色有多个权限
)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




