package cn.aixan.service;

import cn.aixan.model.domain.Checkitem;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 检查项接口
 *
 * @author aix
 * @description 针对表【t_checkitem(检查项)】的数据库操作Service
 * @createDate 2022-09-02 15:25:09
 */
public interface CheckitemService extends IService<Checkitem> {
    /**
     * 检查项分页条件查询
     *
     * @param queryPage 分页查询对象
     * @return 分页数据
     */
    Page<Checkitem> listPage(QueryPage queryPage);

    /**
     * 添加检查项
     * @param checkItem 检查项信息对象
     * @return 是否添加成功
     */
    boolean addCheckItem(Checkitem checkItem);

    /**
     * 修改检查项
     * @param checkItem 检查项信息
     * @return 是否修改成功
     */
    boolean putCheckItem(Checkitem checkItem);
}
