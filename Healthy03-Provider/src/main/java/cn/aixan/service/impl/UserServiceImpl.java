package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.User;
import cn.aixan.service.UserService;
import cn.aixan.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_user(员工表)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:10
*/
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




