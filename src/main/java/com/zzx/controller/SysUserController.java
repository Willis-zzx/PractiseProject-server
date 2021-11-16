package com.zzx.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zzx.domain.BaseController;
import com.zzx.constant.UserConstants;
import com.zzx.domain.AjaxResult;
import com.zzx.domain.TableDataInfo;
import com.zzx.domain.entity.SysRole;
import com.zzx.domain.entity.SysUser;
import com.zzx.service.SysPostService;
import com.zzx.service.SysRoleService;
import com.zzx.service.SysUserService;
import com.zzx.utils.SecurityUtils;
import com.zzx.utils.StringUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.zzx.utils.SecurityUtils.getUsername;

/**
 * 用户信息控制类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/10 21:11
 */
@Api(tags = "user接口")
@ApiSupport(author = "Willis-zzx", order = 284)
@RestController
@RequestMapping(value = "/api/system/user")
public class SysUserController extends BaseController {

    private SysUserService userService;

    private SysRoleService roleService;

    private SysPostService postService;

    @Autowired
    private void setService(SysUserService userService, SysRoleService roleService, SysPostService postService) {
        this.userService = userService;
        this.roleService = roleService;
        this.postService = postService;
    }

    /**
     * 查询所有用户资料
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = userService.selectUserList(sysUser);
        return getDataTable(list);
    }


    /**
     * 通过id查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息集合信息
     */
    @GetMapping(value = {"/getUserById/{userId}", "getUserById"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        //检查用户是否有权限
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     *
     * @param sysUser 用户信息
     * @return 新增结果信息
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysUser sysUser) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(sysUser.getUserName()))) {
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(sysUser.getPhoneNumber()) && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser))) {
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(sysUser.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser))) {
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
        }
        sysUser.setCreateBy(getUsername());
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return toAjax(userService.insertUser(sysUser));
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改结果信息
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhoneNumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     *
     * @param userIds 用户id集合
     * @return 删除结果信息
     */
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
//        if (ArrayUtils.contains(userIds, getUserId()))
//        {
//            return error("当前用户不能删除");
//        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 导出用户数据
     *
     * @param user
     * @return
     */
    @GetMapping("/export")
    public AjaxResult export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        // ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        // return util.exportExcel(list, "用户数据");
        return null;
    }

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 用户状态修改
     *
     * @param user
     * @return
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
}
