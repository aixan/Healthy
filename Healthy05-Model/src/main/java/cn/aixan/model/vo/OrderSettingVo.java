package cn.aixan.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预约视图对象
 *
 * @author aix QQ:32729842
 * @version 2022-09-08 10:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSettingVo implements Serializable {
    private static final long serialVersionUID = 1952761965520805144L;
    private Integer date;
    private Integer number;
    private Integer reservations;
}
