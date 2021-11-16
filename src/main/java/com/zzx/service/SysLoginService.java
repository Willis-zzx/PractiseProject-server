package com.zzx.service;

/**
 * 登录校验方法
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/12 20:18
 */
public interface SysLoginService {
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    String login(String username, String password, String code, String uuid);
}
