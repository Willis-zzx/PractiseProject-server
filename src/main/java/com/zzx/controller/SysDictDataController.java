package com.zzx.controller;

import com.zzx.domain.AjaxResult;
import com.zzx.domain.BaseController;
import com.zzx.domain.TableDataInfo;
import com.zzx.domain.entity.SysDictData;
import com.zzx.service.SysDictDataService;
import com.zzx.service.SysDictTypeService;
import com.zzx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息控制类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2021/10/23 16:35
 */
@RestController
@RequestMapping("/api/system/dict/data")
public class SysDictDataController extends BaseController {

    private SysDictDataService dictDataService;
    private SysDictTypeService dictTypeService;

    @Autowired
    private void setService(SysDictDataService dictDataService, SysDictTypeService dictTypeService) {
        this.dictDataService = dictDataService;
        this.dictTypeService = dictTypeService;
    }

    /**
     * @param dictData
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<>();
        }
        return AjaxResult.success(data);
    }
}
