package com.zzx.exception;

/**
 * 用户信息异常类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/13 16:37
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
