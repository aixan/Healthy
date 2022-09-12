package cn.aixan.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aix QQ:32729842
 * @version 2022-09-09 19:32
 */
@Data
public class OrderVo implements Serializable {
    private static final long serialVersionUID = 3667251730693377277L;

    private String name;
    private Integer sex;
    private String phone;
    private String idCard;
    private String orderDate;
    private Integer setMealId;
    private String setMealName;
}
