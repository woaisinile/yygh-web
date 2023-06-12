package com.atguigu.com.servicehosp.service.impl;

import com.atguigu.com.model.model.hosp.HospitalSet;
import com.atguigu.com.servicehosp.mapper.HospitalSetMapper;
import com.atguigu.com.servicehosp.service.HospitalSetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Override
    public String getSignKey(String hosCode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hosCode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}
