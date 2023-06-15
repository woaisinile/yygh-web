package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Hospital;
import com.atguigu.com.model.vo.hosp.HospitalQueryVo;

import java.util.Map;

public interface HospitalService {

    public void save(Map<String, Object> paramMap);

    Hospital getByHosCode(Object hoscode);

    Object selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新上线状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 医院详情
     * @param id
     * @return
     */
    Map<String, Object> show(String id);
}
