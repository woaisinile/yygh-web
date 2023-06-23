package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Department;
import com.atguigu.com.model.vo.hosp.DepartmentQueryVo;
import com.atguigu.com.model.vo.hosp.DepartmentVo;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> selectPage(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);

    //根据科室编号，和医院编号，查询科室名称
    String getDepName(String hoscode, String depcode);
}
