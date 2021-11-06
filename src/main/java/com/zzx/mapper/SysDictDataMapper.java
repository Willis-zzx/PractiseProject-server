package com.zzx.mapper;

import com.zzx.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/23 16:58
 */
@Mapper
public interface SysDictDataMapper {

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(String dictType);
}
