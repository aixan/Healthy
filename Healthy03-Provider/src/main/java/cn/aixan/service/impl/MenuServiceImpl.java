package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Menu;
import cn.aixan.service.MenuService;
import cn.aixan.mapper.MenuMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_menu(菜单表)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




