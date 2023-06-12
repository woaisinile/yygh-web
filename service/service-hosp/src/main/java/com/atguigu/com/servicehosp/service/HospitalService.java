package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {

    public void save(Map<String, Object> paramMap);

    Object getByHoscode(String hoscode);
}
