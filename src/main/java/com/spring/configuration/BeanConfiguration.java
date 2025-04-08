package com.spring.configuration;

import com.spring.serivces.*;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfiguration implements AsyncConfigurer {

//    @Bean
//    public Address returnAddressObject(){
//        return new Address();
//    }

    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public Executor getAsyncExecutor(){
        if (taskExecutor==null){
             taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setMaxPoolSize(4);
            taskExecutor.setCorePoolSize(2);
            taskExecutor.setQueueCapacity(3);
            taskExecutor.setThreadNamePrefix("MyDefaultThread-");
            taskExecutor.initialize();
        }

        return taskExecutor;
    }



    @Bean
    public Order getOrderObject(@Value("${isOnlineOrder}") boolean isOnlineOrder){
        if (isOnlineOrder){
            return  new OnlineOrders();
        }else {
            return  new OfflineOrders();
        }
    }

    @Bean
    @ConditionalOnProperty(name = "app.createObject" , havingValue = "true",matchIfMissing = false)
    public MySqlConnection getMySQlConnection(){
        return new MySqlConnection();
    }



//    @Bean
//    public DataSource getDataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/platform_data_cwmi?useSSL=false&serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }

//    @Bean
//    public DataSource getDataSource(){
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setJdbcUrl("jdbc:h2:mem:studentDB");
//        dataSource.setUsername("sa");
//        return dataSource;
//    }

//    @Bean
//    public PlatformTransactionManager getUserSpecificTransactionManager(DataSource dataSource){
//        return  new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public TransactionTemplate getTransactionTemplateBean(PlatformTransactionManager platformTransactionManager){
//        return  new TransactionTemplate(platformTransactionManager);
//    }



    @Bean(name = "customThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutorObject(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setQueueCapacity(3);
        taskExecutor.setThreadNamePrefix("MyThread-");
        taskExecutor.initialize();
        return taskExecutor;
    }


    @Bean(name = "plainJavaThreadPoolExecutor")
    public ThreadPoolExecutor getJavaThreadPoolEXecutor(){
        ThreadPoolExecutor javaExecutor = new ThreadPoolExecutor(2,4,1, TimeUnit.HOURS,new ArrayBlockingQueue<>(3));
        return javaExecutor;
    }

}
