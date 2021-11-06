package com.zzx.controller;

import com.zzx.domain.BaseController;
import com.zzx.domain.entity.SysRole;
import com.zzx.constant.UserConstants;
import com.zzx.domain.AjaxResult;
import com.zzx.domain.TableDataInfo;
import com.zzx.service.SysRoleService;
import com.zzx.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息控制类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/10 23:14
 */
@RestController
@RequestMapping("/api/system/role")
public class SysRoleController extends BaseController {

    private SysRoleService roleService;

    @Autowired
    private void setService(SysRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 查询权限列表
     *
     * @param sysRole 权限信息
     * @return 权限信息集合信息
     */
    @GetMapping("/list")
    public TableDataInfo selectRoleList(SysRole sysRole) {
        startPage();
        List<SysRole> sysRoleList = roleService.selectRoleList(sysRole);
        return getDataTable(sysRoleList);
    }

    /**
     * 新增角色
     *
     * @param sysRole 角色信息
     * @return 角色信息集合
     */
    @PostMapping("/insertSysRole")
    public AjaxResult insertSysRole(SysRole sysRole) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(sysRole))) {
            return AjaxResult.error("新增角色'" + sysRole.getRoleName() + "'失败，角色名称已存在。");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(sysRole))) {
            return AjaxResult.error("新增角色'" + sysRole.getRoleName() + "'失败，角色权限已存在");
        }
        sysRole.setCreateTime(DateUtils.getNowTimestamp());
        sysRole.setUpdateTime(DateUtils.getNowTimestamp());
        return toAjax(roleService.insertSysRole(sysRole));
    }
}
