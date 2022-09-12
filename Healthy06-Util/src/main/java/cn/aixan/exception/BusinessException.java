package cn.aixan.exception;

import cn.aixan.common.ErrorCode;

/**
 * 自定义异常类
 *
 * @author aix QQ:32729842
 * @version 2022-09-07 09:11
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -8639638683096739103L;
    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode,String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
