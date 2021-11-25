package com.zzx.controller;

import com.zzx.constant.UserConstants;
import com.zzx.domain.AjaxResult;
import com.zzx.domain.BaseController;
import com.zzx.domain.TableDataInfo;
import com.zzx.domain.entity.SysPost;
import com.zzx.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zzx.utils.SecurityUtils.getUsername;

/**
 * 岗位信息操作处理
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/23 14:47
 */
@RestController
@RequestMapping("/api/system/post")
public class SysPostController extends BaseController {

    private SysPostService postService;

    @Autowired
    private void setPostService(SysPostService postService) {
        this.postService = postService;
    }

    /**
     * 获取岗位列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }


    @GetMapping("/export")
    public AjaxResult export(SysPost post) {
        List<SysPost> list = postService.selectPostList(post);
        //ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        //return util.exportExcel(list, "岗位数据");
        return null;
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Long postId) {
        return AjaxResult.success(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(getUsername());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(getUsername());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionSelect")
    public AjaxResult optionSelect() {
        List<SysPost> posts = postService.selectPostAll();
        return AjaxResult.success(posts);
    }
}
