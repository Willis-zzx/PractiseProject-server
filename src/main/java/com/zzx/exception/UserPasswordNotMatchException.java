package com.zzx.exception;

/**
 * 用户密码不正确或不符合规范异常类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/13 16:37
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}

