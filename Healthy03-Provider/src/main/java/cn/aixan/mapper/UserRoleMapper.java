package cn.aixan.mapper;

import cn.aixan.model.domain.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author aix
* @description 针对表【t_user_role(员工表与角色表的中间表，一个员工有多个角色)】的数据库操作Mapper
* @createDate 2022-09-02 15:25:10
* @Entity cn.aixan.model.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




