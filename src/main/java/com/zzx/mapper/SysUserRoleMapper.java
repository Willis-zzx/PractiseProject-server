package com.zzx.mapper;

import com.zzx.domain.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户-角色
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/14 16:44
 */
@Mapper
public interface SysUserRoleMapper {

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    int batchUserRole(List<SysUserRole> userRoleList);
}
