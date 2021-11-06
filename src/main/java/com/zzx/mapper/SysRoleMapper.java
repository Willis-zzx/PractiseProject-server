package com.zzx.mapper;

import com.zzx.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/11 20:47
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 查询角色列表
     *
     * @param sysRole 角色信息
     * @return 角色信息集合信息
     */
    List<SysRole> selectRoleList(SysRole sysRole);

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
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    SysRole checkRoleKeyUnique(String roleKey);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Integer> selectRoleListByUserId(Long userId);
}
