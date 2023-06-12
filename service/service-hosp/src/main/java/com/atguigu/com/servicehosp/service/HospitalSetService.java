package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;


public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hosCode);
}
