package com.zzx.controller;

import com.zzx.domain.AjaxResult;
import com.zzx.domain.BaseController;
import com.zzx.domain.TableDataInfo;
import com.zzx.domain.entity.SysDictData;
import com.zzx.service.SysDictDataService;
import com.zzx.service.SysDictTypeService;
import com.zzx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.zzx.utils.SecurityUtils.getUsername;

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

    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }


    @GetMapping("/export")
    public AjaxResult export(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        //ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        //return util.exportExcel(list, "字典数据");
        return null;
    }

    /**
     * 查询字典数据详细
     */
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典类型
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
