package cn.aixan.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果
 *
 * @author aix QQ:32729842
 * @version 2022-09-02 14:48
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -5407436765474461878L;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回描述（详情）
     */
    private String description;
    /**
     * 返回数据
     */
    private T data;
}
