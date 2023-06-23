package com.atguigu.com.servicehosp.service;

import com.atguigu.com.model.model.hosp.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface ScheduleService {

    //根据医院编号 和 科室编号 ，查询排班规则数据
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);
}
