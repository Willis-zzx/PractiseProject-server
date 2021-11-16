package com.zzx.web;

import com.zzx.domain.entity.SysUser;
import com.zzx.domain.model.LoginUser;
import com.zzx.domain.model.UserStatus;
import com.zzx.exception.ServiceException;
import com.zzx.service.SysUserService;
import com.zzx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/10 16:49
 */
@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private SysUserService userService;

    private SysPermissionService permissionService;

    @Autowired
    private void setService(SysUserService userService, SysPermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        System.out.println("zzx" + createLoginUser(user));
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
