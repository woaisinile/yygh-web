package com.atguigu.com.servicehosp.repository;

import com.atguigu.com.model.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    Hospital getHospitalByHoscode(String hoscode);
}
