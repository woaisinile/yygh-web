package com.atguigu.com.servicehosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.com.model.model.hosp.Department;
import com.atguigu.com.servicehosp.repository.DepartmentRepository;
import com.atguigu.com.servicehosp.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap){
        Department department = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Department.class);
        departmentRepository.save(department);

    }
}
