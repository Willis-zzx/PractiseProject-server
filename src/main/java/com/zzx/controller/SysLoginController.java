package com.zzx.controller;

import com.zzx.constant.Constants;
import com.zzx.domain.AjaxResult;
import com.zzx.domain.model.LoginBody;
import com.zzx.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/10 23:10
 */
@RestController
public class SysLoginController {


    private SysLoginService loginService;

    @Autowired
    private void setService(SysLoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        //生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
