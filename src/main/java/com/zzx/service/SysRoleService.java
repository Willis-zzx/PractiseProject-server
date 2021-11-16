package com.zzx.service;

import com.zzx.domain.entity.SysRole;

import java.util.List;
import java.util.Set;

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

    /**
     * 根据角色id获取角色详细信息
     *
     * @param roleId 角色id
     * @return 角色详细信息实体类
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 角色状态修改
     *
     * @param role 角色实体类
     * @return 执行结果
     */
    int updateRoleStatus(SysRole role);

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int authDataScope(SysRole role);

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}
