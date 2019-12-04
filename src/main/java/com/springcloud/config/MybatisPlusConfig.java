package com.springcloud.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 分页
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】   3.2.0 移除了
     */
    // @Bean
    // public PerformanceInterceptor performanceInterceptor() {
    //     return new PerformanceInterceptor();
    // }
  


}