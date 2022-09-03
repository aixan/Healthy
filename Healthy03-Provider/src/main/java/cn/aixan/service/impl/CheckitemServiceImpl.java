package cn.aixan.service.impl;

import cn.aixan.mapper.CheckitemMapper;
import cn.aixan.model.domain.Checkitem;
import cn.aixan.service.CheckitemService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 检查项实现类
 *
 * @author aix
 * @description 针对表【t_checkitem(检查项)】的数据库操作Service实现
 * @createDate 2022-09-02 15:25:09
 */
@DubboService
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements CheckitemService {

    public Page<Checkitem> listPage(QueryPage queryPage) {
        if (queryPage == null) {
            queryPage = new QueryPage(1, 10, "");
        }
        QueryWrapper<Checkitem> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isAnyBlank(queryPage.getQueryString())) {
            queryWrapper.like("name", queryPage.getQueryString()).or().like("code", queryPage.getQueryString());
        }
        Page<Checkitem> page = new Page<>(queryPage.getCurrentPage(), queryPage.getPageSize());
        return this.getBaseMapper().selectPage(page, queryWrapper);
    }

    public boolean addCheckItem(Checkitem checkItem) {
        // 判断checkItem是否为空
        if (checkItem == null) {
            return false;
        }
        // 判断检查项名字是否为空
        if (StringUtils.isAnyBlank(checkItem.getName())) {
            return false;
        }
        // 判断检查项名字是否已存在
        QueryWrapper<Checkitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", checkItem.getName());
        Checkitem checkitem = this.getBaseMapper().selectOne(queryWrapper);
        if (checkitem != null) {
            return false;
        }
        // 删除ID
        checkItem.setId(null);
        // 向数据库插入检查项
        return this.save(checkItem);
    }

    @Override
    public boolean putCheckItem(Checkitem checkItem) {
        // 判断checkItem是否为空
        if (checkItem == null) {
            return false;
        }
        // 判断是否有ID
        if (checkItem.getId() <= 0) {
            return false;
        }
        // 判断检查项名字是否为空
        if (StringUtils.isAnyBlank(checkItem.getName())) {
            return false;
        }
        // 判断检查项名字是否已存在
        QueryWrapper<Checkitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", checkItem.getName());
        Checkitem checkitem = this.getBaseMapper().selectOne(queryWrapper);
        if (checkitem != null && !checkitem.getId().equals(checkItem.getId())) {
            return false;
        }
        // 向数据库更新检查项
        QueryWrapper<Checkitem> updateQueryWrapper = new QueryWrapper<>();
        updateQueryWrapper.eq("id", checkItem.getId());
        return this.update(checkItem, updateQueryWrapper);
    }
}