package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.RoleMenu;
import cn.aixan.service.RoleMenuService;
import cn.aixan.mapper.RoleMenuMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_role_menu(角色表与菜单表的中间表，一个角色有多个菜单)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




