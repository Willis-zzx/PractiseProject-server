package com.zzx.service.impl;

import com.zzx.constant.Constants;
import com.zzx.domain.entity.SysUser;
import com.zzx.domain.model.LoginUser;
import com.zzx.exception.ServiceException;
import com.zzx.exception.UserPasswordNotMatchException;
import com.zzx.redis.RedisCache;
import com.zzx.service.SysLoginService;
import com.zzx.service.SysUserService;
import com.zzx.utils.AsyncManager;
import com.zzx.utils.DateUtils;
import com.zzx.utils.MessageUtils;
import com.zzx.utils.ServletUtils;
import com.zzx.utils.ip.IpUtils;
import com.zzx.web.MyAuthenticationProvider;
import com.zzx.web.TokenService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/12 20:19
 */
@Service("sysLoginService")
public class SysLoginServiceImpl implements SysLoginService {

    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    private RedisCache redisCache;

    private SysUserService userService;

    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private void setService(TokenService tokenService, RedisCache redisCache, SysUserService userService,
                            MyAuthenticationProvider authenticationProvider) {
        this.tokenService = tokenService;
        this.redisCache = redisCache;
        this.userService = userService;
        this.authenticationProvider=authenticationProvider;
    }


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    @Override
    public String login(String username, String password, String code, String uuid) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new ServiceException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            //throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            //throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        //userService.updateUserProfile(user);
    }
}
