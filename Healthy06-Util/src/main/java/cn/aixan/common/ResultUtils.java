package cn.aixan.common;

/**
 * 返回工具类
 *
 * @author aix QQ:32729842
 * @version 2022-09-07 09:02
 */
public class ResultUtils {
    private ResultUtils() {
    }

    /**
     * 请求成功，不需要数据和消息
     *
     * @param <T> 泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(0, "ok", null, null);
    }

    /**
     * 请求成功，返回详情
     *
     * @param <T> 泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> success(String description) {
        return new BaseResponse<>(0, "ok", description, null);
    }

    /**
     * 请求成功，返回详情和数据
     *
     * @param <T> 泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> success(String description, T data) {
        return new BaseResponse<>(0, "ok", description, data);
    }

    /**
     * 请求成功，返回数据
     *
     * @param <T> 泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> successToData(T data) {
        return new BaseResponse<>(0, "ok", null, data);
    }

    /**
     * 请求失败
     *
     * @param errorCode 全局错误码
     * @param <T>       泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), errorCode.getDescription(), null);
    }

    /**
     * 请求失败，自定义描述（详情）
     *
     * @param errorCode   全局错误码
     * @param description 描述（详情）
     * @param <T>         泛型
     * @return 返回信息
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), description, null);
    }

    /**
     * 请求失败，自定义消息和描述（详情）
     *
     * @param errorCode   全局错误码
     * @param message     自定义消息
     * @param description 自定义描述（详情）
     * @param <T>         泛型
     * @return 返回消息
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), message, description, null);
    }

    /**
     * 请求失败，自定义返回
     *
     * @param code        自定义返回码
     * @param message     自定义消息
     * @param description 自定义描述（详情）
     * @param <T>         泛型
     * @return 返回消息
     */
    public static <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse<>(code, message, description, null);
    }
}
