package com.zzx.domain.model;

/**
 * 用户状态
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/10 16:50
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
