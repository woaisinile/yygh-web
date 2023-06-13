package com.atguigu.com.servicehosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.com.model.model.hosp.Schedule;
import com.atguigu.com.servicehosp.repository.ScheduleRepository;
import com.atguigu.com.servicehosp.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Schedule schedule = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Schedule.class);
        Schedule targetSchedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());
        if(null != targetSchedule) {
            //值copy不为null的值，该方法为自定义方法
            BeanUtils.copyProperties(schedule, targetSchedule, Schedule.class);
            scheduleRepository.save(targetSchedule);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }
    }
}
