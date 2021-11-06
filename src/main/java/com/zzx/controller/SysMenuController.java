package com.zzx.controller;

import com.zzx.domain.AjaxResult;
import com.zzx.domain.BaseController;
import com.zzx.domain.entity.SysMenu;
import com.zzx.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/24 20:58
 */
@RestController
@RequestMapping("/api/system/menu")
public class SysMenuController extends BaseController {

    private SysMenuService menuService;

    @Autowired
    private void setService(SysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单信息集合
     */
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu, null);
        return AjaxResult.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     *
     * @param menuId 菜单id
     * @return 封装信息集合
     */
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }
}
