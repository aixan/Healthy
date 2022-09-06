package cn.aixan.service;

import cn.aixan.model.domain.Setmeal;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
