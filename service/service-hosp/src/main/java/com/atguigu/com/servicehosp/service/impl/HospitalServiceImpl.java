package com.atguigu.com.servicehosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.com.model.model.hosp.Hospital;
import com.atguigu.com.servicehosp.repository.HospitalRepository;
import com.atguigu.com.servicehosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Hospital.class);
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if (null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setUpdateTime(new Date());
            hospital.setCreateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }

    }

    @Override
    public Hospital getByHosCode(Object hoscode) {
        return hospitalRepository.getHospitalByHoscode((String) hoscode);
    }
}
