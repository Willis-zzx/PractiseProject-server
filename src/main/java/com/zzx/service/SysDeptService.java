package com.zzx.service;

import com.zzx.domain.TreeSelect;
import com.zzx.domain.entity.SysDept;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/17 14:33
 */
public interface SysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    List<SysDept> buildDeptTree(List<SysDept> deptList);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param deptList 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> deptList);
}
