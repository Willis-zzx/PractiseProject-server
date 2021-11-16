package com.zzx.mapper;

import com.zzx.domain.entity.SysUserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 用户-岗位
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/11/14 16:39
 */
@Mapper
public interface SysUserPostMapper {
    /**
     * 批量新增用户岗位信息
     *
     * @param userPostList 用户角色列表
     * @return 结果
     */
    int batchUserPost(List<SysUserPost> userPostList);
}
