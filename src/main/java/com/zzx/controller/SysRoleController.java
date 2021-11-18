package com.zzx.controller;

import com.zzx.domain.BaseController;
import com.zzx.domain.entity.SysRole;
import com.zzx.constant.UserConstants;
import com.zzx.domain.AjaxResult;
import com.zzx.domain.TableDataInfo;
import com.zzx.service.SysRoleService;
import com.zzx.utils.DateUtils;
import org.apache.catalina.User;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zzx.utils.SecurityUtils.getUsername;

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

    /**
     * 根据角色id获取角色详细信息
     *
     * @param roleId 角色id
     * @return 角色信息合集
     */
    @GetMapping(value = "/getRole/{roleId}")
    public AjaxResult getRole(@PathVariable(value = "roleId") Long roleId) {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 角色状态修改
     *
     * @param role 角色实体类
     * @return 执行结果
     */
    @PutMapping(value = "/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role) {
        // 设置更新者
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色实体类
     * @return 执行结果
     */
    @PutMapping(value = "/updateRole")
    public AjaxResult updateRole(@Validated @RequestBody SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("修改角色' " + role.getRoleName() + " '失败，角色名称已存在！");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return AjaxResult.error("修改角色' " + role.getRoleName() + " '失败，角色权限已存在！");
        }
        //设置更新者
        role.setUpdateBy(getUsername());
        if (roleService.updateRole(role) > 0) {
            //更新缓存用户权限
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @PutMapping(value = "dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role) {
        return toAjax(roleService.authDataScope(role));
    }
}
