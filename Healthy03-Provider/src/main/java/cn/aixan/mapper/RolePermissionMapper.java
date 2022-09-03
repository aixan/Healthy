package cn.aixan.mapper;

import cn.aixan.model.domain.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author aix
* @description 针对表【t_role_permission(角色与权限的中间表，一个角色有多个权限
)】的数据库操作Mapper
* @createDate 2022-09-02 15:25:10
* @Entity cn.aixan.model.domain.RolePermission
*/
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}




