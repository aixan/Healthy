package cn.aixan.service;

import cn.aixan.model.domain.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author aix
* @description 针对表【t_role_permission(角色与权限的中间表，一个角色有多个权限
)】的数据库操作Service
* @createDate 2022-09-02 15:25:10
*/
public interface RolePermissionService extends IService<RolePermission> {

}
