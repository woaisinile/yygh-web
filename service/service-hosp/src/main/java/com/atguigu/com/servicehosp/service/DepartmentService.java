package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Department;
import com.atguigu.com.model.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.*;

import java.util.Map;

public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> selectPage(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);
}
