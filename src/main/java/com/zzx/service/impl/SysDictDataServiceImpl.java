package com.zzx.service.impl;

import com.zzx.domain.entity.SysDictData;
import com.zzx.mapper.SysDictDataMapper;
import com.zzx.service.SysDictDataService;
import com.zzx.utils.DictUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典信息服务类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/23 16:55
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl implements SysDictDataService {

    @Resource
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictData data = selectDictDataById(dictCode);
            dictDataMapper.deleteDictDataById(dictCode);
            List<SysDictData> dictData = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictData);
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData) {
        int row = dictDataMapper.insertDictData(dictData);
        if (row > 0) {
            List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictData.getDictType());
            DictUtils.setDictCache(dictData.getDictType(), dictDataList);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData) {
        int row = dictDataMapper.updateDictData(dictData);
        if (row > 0) {
            List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictData.getDictType());
            DictUtils.setDictCache(dictData.getDictType(), dictDataList);
        }
        return row;
    }
}
