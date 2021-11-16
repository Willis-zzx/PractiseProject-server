package com.zzx.mapper;

import com.zzx.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/24 21:00
 */
@Mapper
public interface SysMenuMapper {
    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);
}
