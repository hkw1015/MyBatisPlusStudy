package com.hkw.mpdemo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus配置类
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.hkw.mpdemo.mapper")
public class MyBatisPlusConfig {
    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector iSqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * #环境设置：dev、test、prod
     * spring.profiles.active=dev
     * 可以针对各环境新建不同的配置文件application-dev.properties、application-test.properties、application-prod.properties
     * 也可以自定义环境名称：如test1、test2
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(500);//ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setFormat(true);// sql是否格式化，默认为false
        return performanceInterceptor;
    }
}
