package cn.aixan.service;

import cn.aixan.model.domain.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author aix
* @description 针对表【t_user_role(员工表与角色表的中间表，一个员工有多个角色)】的数据库操作Service
* @createDate 2022-09-02 15:25:10
*/
public interface UserRoleService extends IService<UserRole> {

}
