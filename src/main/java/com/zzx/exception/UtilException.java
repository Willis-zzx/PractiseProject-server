package com.zzx.exception;

/**
 * 工具类异常
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/10 23:00
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
