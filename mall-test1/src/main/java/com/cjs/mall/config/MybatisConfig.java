package com.cjs.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mybatis配置类
 * @author: cuijunsheng
 * @date: 2020-01-13 14:04
 **/
@Configuration
@MapperScan(value = {"com.cjs.mall.mbg.mapper"})
public class MybatisConfig {
}
