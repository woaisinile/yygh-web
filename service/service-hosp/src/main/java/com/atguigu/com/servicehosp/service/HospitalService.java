package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Hospital;
import com.atguigu.com.model.vo.hosp.HospitalQueryVo;

import java.util.Map;

public interface HospitalService {

    public void save(Map<String, Object> paramMap);

    Hospital getByHosCode(Object hoscode);

    Object selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}
