package cn.aixan.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询
 * @author aix QQ:32729842
 * @version 2022-09-02 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPage implements Serializable {
    private static final long serialVersionUID = 7472251694209641705L;
    /**
     * 页码
     */
    private Integer currentPage;
    /**
     * 页数
     */
    private Integer pageSize;
    /**
     * 查询条件
     */
    private String queryString;
}
