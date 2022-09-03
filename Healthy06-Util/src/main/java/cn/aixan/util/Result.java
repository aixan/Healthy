package cn.aixan.util;

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
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5407436765474461878L;

    private static final Integer SUCCESS = 200;
    private static final Integer FAILED = 500;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 请求成功不需要返回数据和消息
     *
     * @param <Type> 泛型
     * @return 返回体
     */
    public static <Type> Result<Type> success() {
        return new Result<>(SUCCESS, null, null);
    }

    /**
     * 请求成功，需要返回数据
     *
     * @param data   返回数据
     * @param <Type> 泛型
     * @return 返回体
     */
    public static <Type> Result<Type> success(Type data) {
        return new Result<>(SUCCESS, null, data);
    }

    /**
     * 请求失败，不需要返回数据和消息
     *
     * @param <Type> 泛型
     * @return 返回体
     */
    public static <Type> Result<Type> failed() {
        return new Result<>(FAILED, null, null);
    }

    /**
     * 请求失败，返回消息
     * @param message 失败消息
     * @return 返回体
     * @param <Type> 泛型
     */
    public static <Type> Result<Object> failed(String message) {
        return new Result<>(FAILED, message, null);
    }

}
