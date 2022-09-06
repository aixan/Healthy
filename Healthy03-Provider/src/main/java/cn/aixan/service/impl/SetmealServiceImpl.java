package cn.aixan.service.impl;

import cn.aixan.constant.MessageConstant;
import cn.aixan.mapper.SetmealMapper;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.service.SetmealService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 套餐接口实现类
 *
 * @author aix
 * @description 针对表【t_setmeal(套餐)】的数据库操作Service实现
 * @createDate 2022-09-02 15:25:10
 */
@DubboService
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
        implements SetmealService {

    @Override
    public Page<Setmeal> getCheckGroupPage(QueryPage queryPage) {
        if (queryPage == null) {
            throw new RuntimeException(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        String queryString = queryPage.getQueryString();
        Integer currentPage = queryPage.getCurrentPage();
        Integer pageSize = queryPage.getPageSize();
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)){
            queryWrapper.like("code",queryString)
                    .or().like("name",queryString)
                    .or().like("helpCode",queryString);
        }
        Page<Setmeal> page = new Page<>(currentPage,pageSize);
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }
}




