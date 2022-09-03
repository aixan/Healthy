package cn.aixan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aixan.model.domain.Member;
import cn.aixan.service.MemberService;
import cn.aixan.mapper.MemberMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
* @author aix
* @description 针对表【t_member(会员表)】的数据库操作Service实现
* @createDate 2022-09-02 15:25:09
*/
@DubboService
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




