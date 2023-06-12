package com.atguigu.com.servicehosp.controller;

import com.atguigu.com.commonutil.exception.YyghException;
import com.atguigu.com.commonutil.result.Result;
import com.atguigu.com.commonutil.result.ResultCodeEnum;
import com.atguigu.com.model.model.hosp.Department;
import com.atguigu.com.model.model.hosp.Hospital;
import com.atguigu.com.model.vo.hosp.DepartmentQueryVo;
import com.atguigu.com.servicehosp.service.DepartmentService;
import com.atguigu.com.servicehosp.service.HospitalService;
import com.atguigu.com.servicehosp.service.HospitalSetService;
import com.atguigu.com.serviceutil.helper.HttpRequestHelper;
import com.atguigu.com.serviceutil.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //签名校验
        if(!HttpRequestHelper.isSignEquals(paramMap, hospitalSetService.getSignKey(hoscode))) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        return Result.ok(hospitalService.getByHoscode((String)paramMap.get("hoscode")));
    }

    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hospSign = (String) paramMap.get("sign");
        String hosCode = (String)  paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hosCode);

        String signKeyMd5 = MD5.encrypt(signKey);

        if (!hospSign.equals(signKeyMd5)){
            throw  new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData",logoData);
        hospitalService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) paramMap.get("hoscode");
        if(StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        departmentService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取分页列表")
    @PostMapping("department/list")
    public Result department(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //必须参数校验 略
        String hoscode = (String)paramMap.get("hoscode");
        //非必填
        String depcode = (String)paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        departmentQueryVo.setDepcode(depcode);
        Page<Department> pageModel = departmentService.selectPage(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }
}
