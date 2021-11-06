package com.zzx.mapper;

import com.zzx.domain.entity.SysPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 岗位信息
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/24 14:48
 */
@Mapper
public interface SysPostMapper {
    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPost> selectPostAll();
}
