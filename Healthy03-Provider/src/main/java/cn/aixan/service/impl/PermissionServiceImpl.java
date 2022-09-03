package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Permission;
import cn.aixan.service.PermissionService;
import cn.aixan.mapper.PermissionMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_permission(权限表)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




