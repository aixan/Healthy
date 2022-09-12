package cn.aixan.service.impl;

import cn.aixan.common.ErrorCode;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.mapper.CheckgroupMapper;
import cn.aixan.model.domain.Checkgroup;
import cn.aixan.model.domain.CheckgroupCheckitem;
import cn.aixan.service.CheckgroupService;
import cn.aixan.util.Pinyin4jUtils;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 检查组实现类
 *
 * @author aix
 * @description 针对表【t_checkgroup(检查组)】的数据库操作Service实现
 * @createDate 2022-09-02 15:25:09
 */
@DubboService
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup>
        implements CheckgroupService {

    @Resource
    private CheckgroupCheckitemServiceImpl checkgroupCheckitemService;

    @Override
    public Page<Checkgroup> getCheckGroupPage(QueryPage queryPage) {
        // 判断queryPage是否为空
        if (queryPage == null) {
            queryPage = new QueryPage(1, 10, "");
        }
        String queryString = queryPage.getQueryString();
        Integer currentPage = queryPage.getCurrentPage();
        Integer pageSize = queryPage.getPageSize();
        QueryWrapper<Checkgroup> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)) {
            queryWrapper.like("code", queryString)
                    .or().like("name", queryString)
                    .or().like("helpCode", queryString);
        }
        Page<Checkgroup> page = new Page<>(currentPage, pageSize);
        return this.getBaseMapper().selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCheckGroupRelationCheckItem(Checkgroup checkgroup, Integer[] checkItemId) {
        if (checkgroup == null || checkItemId.length == 0) {
            return false;
        }
        // 查询检查组名字是否存在
        String name = checkgroup.getName();
        if (StringUtils.isAnyBlank(name)) {
            return false;
        }
        QueryWrapper<Checkgroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return false;
        }
        // 防止传入ID
        checkgroup.setId(null);
        // 如果助记码为空设置检查组名字首字母大写
        String helpCode = checkgroup.getHelpcode();
        if (helpCode == null) {
            String pinyinHeadChar = Pinyin4jUtils.getPinyinHeadChar(name);
            checkgroup.setHelpcode(pinyinHeadChar.toUpperCase());
        }
        boolean saveResult = this.save(checkgroup);
        if (!saveResult) {
            throw new RuntimeException(MessageConstant.ADD_CHECK_GROUP_FAIL);
        }
        Integer checkGroupId = checkgroup.getId();
        for (Integer integer : checkItemId) {
            CheckgroupCheckitem checkGroupCheckitem = new CheckgroupCheckitem(checkGroupId, integer);
            int insert = checkgroupCheckitemService.getBaseMapper().insert(checkGroupCheckitem);
            if (insert <= 0) {
                throw new RuntimeException(MessageConstant.ADD_CHECK_GROUP_FAIL);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean putCheckGroupRelationCheckItem(Checkgroup checkgroup, Integer[] checkItemId) {
        if (checkgroup == null || checkgroup.getId() <= 0 || checkItemId.length == 0) {
            return false;
        }
        // 查询检查项名字是否存在
        String name = checkgroup.getName();
        if (StringUtils.isAnyBlank(name)) {
            return false;
        }
        QueryWrapper<Checkgroup> checkQueryWrapper = new QueryWrapper<>();
        checkQueryWrapper.eq("name", name);
        Checkgroup one = getOne(checkQueryWrapper);
        if (one != null && !Objects.equals(one.getId(), checkgroup.getId())) {
            return false;
        }
        // 如果助记码为空设置检查组名字首字母大写
        String helpCode = checkgroup.getHelpcode();
        if (helpCode == null) {
            String pinyinHeadChar = Pinyin4jUtils.getPinyinHeadChar(name);
            checkgroup.setHelpcode(pinyinHeadChar.toUpperCase());
        }
        boolean saveResult = this.updateById(checkgroup);
        if (!saveResult) {
            throw new RuntimeException(MessageConstant.EDIT_CHECK_GROUP_FAIL);
        }
        Integer checkGroupId = checkgroup.getId();
        // 添加
        for (Integer integer : checkItemId) {
            QueryWrapper<CheckgroupCheckitem> checkgroupCheckitemQueryWrapper = new QueryWrapper<>();
            checkgroupCheckitemQueryWrapper.eq("checkgroup_id", checkGroupId);
            checkgroupCheckitemQueryWrapper.eq("checkitem_id", integer);
            long count = checkgroupCheckitemService.count(checkgroupCheckitemQueryWrapper);
            if (count == 0) {
                CheckgroupCheckitem checkGroupCheckitem = new CheckgroupCheckitem(checkGroupId, integer);
                int insert = checkgroupCheckitemService.getBaseMapper().insert(checkGroupCheckitem);
                if (insert <= 0) {
                    throw new RuntimeException(MessageConstant.EDIT_CHECK_GROUP_FAIL);
                }
            }
        }
        // 删除陈旧的
        QueryWrapper<CheckgroupCheckitem> checkgroupCheckitemQueryWrapper = new QueryWrapper<>();
        checkgroupCheckitemQueryWrapper.eq("checkgroup_id", checkGroupId);
        checkgroupCheckitemQueryWrapper.notIn("checkitem_id", Arrays.asList(checkItemId));
        boolean remove = checkgroupCheckitemService.remove(checkgroupCheckitemQueryWrapper);
        if (!remove) {
            throw new RuntimeException(MessageConstant.EDIT_CHECK_GROUP_FAIL);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Long id) {
        if (id <= 0) {
            throw new RuntimeException("检查组ID错误");
        }
        return this.removeById(id);
    }

    @Override
    public Checkgroup getCheckGroupById(Integer id) {
        if (id == null || id < 0) {
            throw new BusinessException(ErrorCode.NULL_ERROR,MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        Checkgroup checkgroup = this.getById(id);
        if (checkgroup == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,MessageConstant.QUERY_CHECK_ITEM_FAIL);
        }
        // 根据检查组ID查询所有关联表数据
        QueryWrapper<CheckgroupCheckitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("checkgroup_id", id);
        List<CheckgroupCheckitem> list = checkgroupCheckitemService.list(queryWrapper);
        if (list == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,MessageConstant.QUERY_CHECK_GROUP_ITEM_SUCCESS);
        }
        // 查出关联表数据遍历检查项ID
        List<Integer> collect = list.stream().map(CheckgroupCheckitem::getCheckitemId).collect(Collectors.toList());
        checkgroup.setCheckItemIds(collect);
        return checkgroup;
    }
}




