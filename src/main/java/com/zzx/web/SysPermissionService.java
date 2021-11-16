package com.zzx.web;

import java.util.HashSet;
import java.util.Set;

import com.zzx.domain.entity.SysUser;
import com.zzx.service.SysMenuService;
import com.zzx.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户权限处理
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/10 16:52
 */
@Component
public class SysPermissionService {

    private SysRoleService roleService;

    private SysMenuService menuService;

    @Autowired
    private void setService(SysRoleService roleService, SysMenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
