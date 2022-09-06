package cn.aixan.service;

import cn.aixan.model.domain.CheckgroupCheckitem;
import cn.aixan.model.domain.Checkitem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author aix
* @description 针对表【t_checkgroup_checkitem(栓查项与检查组的中间表，一个检查组有多个检查项)】的数据库操作Service
* @createDate 2022-09-02 15:25:09
*/
public interface CheckgroupCheckitemService extends IService<CheckgroupCheckitem> {
    /**
     * 根据检查组查询关联的检查项ID
     * @param id 检查组ID
     * @return 检查项集合
     */
    List<Integer> getCheckItemByGroupId(Integer id);
}
