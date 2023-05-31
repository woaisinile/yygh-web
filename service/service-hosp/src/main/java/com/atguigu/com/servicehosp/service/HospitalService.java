package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {

    public void save(Map<String, Object> paramMap);

    Hospital getByHosCode(Object hoscode);
}
