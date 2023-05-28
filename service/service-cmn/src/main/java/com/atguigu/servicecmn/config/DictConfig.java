package com.atguigu.servicecmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atguigu.servicecmn.mapper")
public class DictConfig {
}
