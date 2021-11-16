package com.zzx.service.impl;

import com.zzx.domain.entity.SysRole;
import com.zzx.constant.UserConstants;
import com.zzx.mapper.SysRoleMapper;
import com.zzx.service.SysRoleService;
import com.zzx.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/11 20:46
 */
@Service("roleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    /**
     * 查询角色列表
     *
     * @param sysRole 角色信息
     * @return 角色信息集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole sysRole) {
        return roleMapper.selectRoleList(sysRole);
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    /**
     * 增加一个角色
     *
     * @param sysRole 角色信息
     * @return 是否新增成功
     */
    @Override
    public int insertSysRole(SysRole sysRole) {
        return roleMapper.insertSysRole(sysRole);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole sysRole) {
        Long roleId = StringUtils.isNull(sysRole.getRoleId()) ? -1L : sysRole.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique(sysRole.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole sysRole) {
        Long roleId = StringUtils.isNull(sysRole.getRoleId()) ? -1L : sysRole.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(sysRole.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 根据角色id获取角色详细信息
     *
     * @param roleId 角色id
     * @return 角色详细信息实体类
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 角色状态修改
     *
     * @param role 角色实体类
     * @return 执行结果
     */
    @Override
    public int updateRoleStatus(SysRole role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(SysRole role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int authDataScope(SysRole role) {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        // roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        // return insertRoleDept(role);
        return 0;
    }

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        return null;
    }
}
