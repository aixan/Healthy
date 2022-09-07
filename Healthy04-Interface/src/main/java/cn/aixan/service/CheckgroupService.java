package cn.aixan.service;

import cn.aixan.model.domain.Checkgroup;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author aix
* @description 针对表【t_checkgroup(检查组)】的数据库操作Service
* @createDate 2022-09-02 15:25:09
*/
public interface CheckgroupService extends IService<Checkgroup> {

    /**
     * 查询检查组分页信息
     * @param queryPage 查询条件
     * @return 检查组分页信息
     */
    Page<Checkgroup> getCheckGroupPage(QueryPage queryPage);

    /**
     * 添加检查组
     * @param checkgroup 检查组信息
     * @param checkItemId 关联检查项
     * @return 添加结果
     */
    boolean addCheckGroupRelationCheckItem(Checkgroup checkgroup, Integer[] checkItemId);

    /**
     * 编辑检查组
     * @param checkgroup 检查组信息
     * @param checkItemId 关联检查项
     * @return 编辑结果
     */
    boolean putCheckGroupRelationCheckItem(Checkgroup checkgroup, Integer[] checkItemId);

    /**
     * 检查组删除，并删除关联表数据
     * @param id 检查组ID
     * @return 删除结果
     */
    boolean deleteById(Long id);

    /**
     * 获取检查组信息
     * @param id 检查组ID
     * @return 检查组信息
     */
    Checkgroup getCheckGroupById(Integer id);
}
