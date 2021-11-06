package com.zzx.service.impl;

import com.zzx.domain.entity.SysRole;
import com.zzx.constant.UserConstants;
import com.zzx.mapper.SysRoleMapper;
import com.zzx.service.SysRoleService;
import com.zzx.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
