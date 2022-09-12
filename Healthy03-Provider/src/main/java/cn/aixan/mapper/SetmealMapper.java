package cn.aixan.mapper;

import cn.aixan.model.domain.Setmeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author aix
* @description 针对表【t_setmeal(套餐)】的数据库操作Mapper
* @createDate 2022-09-02 15:25:10
* @Entity cn.aixan.model.domain.Setmeal
*/
public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 查询套餐详情
     * @param id 套餐ID
     * @return 套餐详情
     */
    Setmeal selectDetails(Long id);
}




