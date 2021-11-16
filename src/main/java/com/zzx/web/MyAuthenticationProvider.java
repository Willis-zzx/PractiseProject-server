package com.zzx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/14 15:02
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取用户输入的用户名和密码
        final String userName = authentication.getName();
        final String password = authentication.getCredentials().toString();
        //获取封装用户信息的对象
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
        //进行密码的比对
        boolean flag = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        //校验通过
        if (flag) {
            //将权限信息也封装进去
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }
        throw new AuthenticationException("用户密码错误") {
        };
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
