package com.zzx.service.impl;

import com.zzx.domain.entity.SysDictData;
import com.zzx.domain.entity.SysDictType;
import com.zzx.mapper.SysDictDataMapper;
import com.zzx.mapper.SysDictTypeMapper;
import com.zzx.service.SysDictTypeService;
import com.zzx.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/23 17:16
 */
@Service("sysDictTypeService")
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Resource
    private SysDictTypeMapper dictTypeMapper;

    @Resource
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return null;
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return null;
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        //后面加一个从redis查询
        List<SysDictData> dictDataList = new ArrayList<>();
        if (StringUtils.isNotEmpty(dictType)) {
            dictDataList = dictDataMapper.selectDictDataByType(dictType);
        }
        if (StringUtils.isNotEmpty(dictDataList)) {
            return dictDataList;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return null;
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return null;
    }

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {

    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {

    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {

    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {

    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        return 0;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(SysDictType dictType) {
        return 0;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dictType) {
        return null;
    }
}
