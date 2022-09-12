package cn.aixan.service.impl;

import cn.aixan.common.ErrorCode;
import cn.aixan.constant.MessageConstant;
import cn.aixan.exception.BusinessException;
import cn.aixan.mapper.SetmealMapper;
import cn.aixan.model.domain.Setmeal;
import cn.aixan.model.domain.SetmealCheckgroup;
import cn.aixan.service.SetmealCheckgroupService;
import cn.aixan.service.SetmealService;
import cn.aixan.util.Pinyin4jUtils;
import cn.aixan.util.QueryPage;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Resource
    private SetmealCheckgroupService setmealCheckgroupService;

    @Override
    public Page<Setmeal> getCheckGroupPage(QueryPage queryPage) {
        if (queryPage == null) {
            throw new RuntimeException(MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        String queryString = queryPage.getQueryString();
        Integer currentPage = queryPage.getCurrentPage();
        Integer pageSize = queryPage.getPageSize();
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryString)) {
            queryWrapper.like("code", queryString)
                    .or().like("name", queryString)
                    .or().like("helpCode", queryString);
        }
        Page<Setmeal> page = new Page<>(currentPage, pageSize);
        return this.getBaseMapper().selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addSetMeal(Setmeal setMeal) {
        // 判断套餐信息是否为空
        if (setMeal == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        String name = setMeal.getName();
        List<Integer> checkGroupIds = setMeal.getCheckGroupIds();
        // 套餐名字是否为空
        if (StringUtils.isAnyBlank(name)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        // 检查组是否为空
        if (checkGroupIds.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, MessageConstant.MESSAGE_ILLEGAL_PARAMETER);
        }
        // 套餐名字是否存在
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", setMeal.getName());
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "套餐已存在");
        }
        boolean saveResult = this.save(setMeal);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_SETMEAL_FAIL);
        }
        Integer id = setMeal.getId();
        // 如果助记码为空设置套餐名字首字母大写
        String helpCode = setMeal.getHelpcode();
        if (helpCode == null) {
            String pinyinHeadChar = Pinyin4jUtils.getPinyinHeadChar(name);
            setMeal.setHelpcode(pinyinHeadChar.toUpperCase());
        }
        checkGroupIds.forEach(checkGroupId -> {
            SetmealCheckgroup setmealCheckgroup = new SetmealCheckgroup();
            setmealCheckgroup.setSetmealId(id);
            setmealCheckgroup.setCheckgroupId(checkGroupId);
            boolean save = setmealCheckgroupService.save(setmealCheckgroup);
            if (!save) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.ADD_SET_MEAL_CHECK_GROUP_FAIL);
            }
        });
        return true;
    }

    @Override
    public Setmeal getSetMealById(Integer id) {
        if (id == null || id < 0) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.MESSAGE_WRONG_PARAMETER);
        }
        Setmeal setmeal = this.getById(id);
        if (setmeal == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.QUERY_CHECK_ITEM_FAIL);
        }
        // 根据检查组ID查询所有关联表数据
        QueryWrapper<SetmealCheckgroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("setmeal_id", id);
        List<SetmealCheckgroup> list = setmealCheckgroupService.list(queryWrapper);
        if (list == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, MessageConstant.QUERY_CHECK_GROUP_ITEM_SUCCESS);
        }
        // 查出关联表数据遍历检查项ID
        List<Integer> collect = list.stream().map(SetmealCheckgroup::getCheckgroupId).collect(Collectors.toList());
        setmeal.setCheckGroupIds(collect);
        return setmeal;
    }

    @Override
    public List<Setmeal> listForDownload(String search) {
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isAnyBlank(search)) {
            queryWrapper.like("name", search)
                    .or().like("code", search)
                    .or().like("helpCode", search);
        }
        List<Setmeal> list = this.list(queryWrapper);
        if (list.isEmpty()) {
            throw new BusinessException(ErrorCode.NULL_ERROR, MessageConstant.QUERY_SET_MEAL_FAIL);
        }

        return list;
    }

    @Override
    public Setmeal getSetMealByIdDetails(Long id) {
        return this.getBaseMapper().selectDetails(id);
    }
}




