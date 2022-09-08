package cn.aixan.service;

import cn.aixan.model.domain.Setmeal;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 套餐接口
 *
 * @author aix
 * @description 针对表【t_setmeal(套餐)】的数据库操作Service
 * @createDate 2022-09-02 15:25:10
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 套餐分页条件查询
     *
     * @param queryPage 分页条件
     * @return 分页信息
     */
    Page<Setmeal> getCheckGroupPage(QueryPage queryPage);

    /**
     * 添加套餐
     * @param setMeal 套餐信息
     * @return 添加结果
     */
    boolean addSetMeal(Setmeal setMeal);

    /**
     * 根据ID获取套餐
     * @param id 套餐ID
     * @return 套餐信息
     */
    Setmeal getSetMealById(Integer id);

    /**
     * 筛选下载套餐信息
     * @param search 筛选条件
     * @return 下载信息
     */
    List<Setmeal> listForDownload(String search);
}
