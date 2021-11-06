package com.zzx.service;

import com.zzx.domain.entity.SysRole;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/11 20:46
 */
public interface SysRoleService {

    /**
     * 查询权限列表
     *
     * @param sysRole 权限信息
     * @return 权限信息集合信息
     */
    List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();

    /**
     * 增加一个角色
     *
     * @param sysRole 角色信息
     * @return 是否新增成功
     */
    int insertSysRole(SysRole sysRole);

    /**
     * 校验角色名称是否唯一
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(SysRole sysRole);

    /**
     * 校验角色权限是否唯一
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    String checkRoleKeyUnique(SysRole sysRole);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Integer> selectRoleListByUserId(Long userId);
}
