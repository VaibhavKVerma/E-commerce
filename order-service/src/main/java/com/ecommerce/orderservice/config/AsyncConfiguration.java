package com.ecommerce.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
    @Bean("ProductCatalogAsyncExecutor")
    public Executor productCatalogAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setCorePoolSize(4);
        executor.setThreadNamePrefix("ProductCatalogAsyncExecutor-");
        executor.initialize();
        return executor;
    }
}
