package com.zzx.controller;

import com.zzx.domain.AjaxResult;
import com.zzx.domain.entity.SysDept;
import com.zzx.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门信息控制类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/17 14:31
 */
@RestController
@RequestMapping(value = "/api/system/dept")
public class SysDeptController {

    private SysDeptService deptService;

    @Autowired
    private void setService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(SysDept dept) {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(deptList));
    }
}
