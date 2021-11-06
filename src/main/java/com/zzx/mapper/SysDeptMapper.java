package com.zzx.mapper;

import com.zzx.domain.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/17 14:34
 */
@Mapper
public interface SysDeptMapper {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(SysDept dept);
}
