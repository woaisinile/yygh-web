package com.atguigu.servicecmn.controller;

import com.atguigu.com.commonutil.result.Result;
import com.atguigu.com.model.model.cmn.Dict;
import com.atguigu.servicecmn.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(description = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData (@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response){
        dictService.exportDictDate(response);
    }

    @PostMapping("importData")
    public void importDict(MultipartFile file){
        dictService.importDictData(file);
    }
}
