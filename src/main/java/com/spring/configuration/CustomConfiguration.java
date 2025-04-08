package com.spring.configuration;


import com.spring.components.CustomFilter;
import com.spring.components.CustomInterceptor;
import com.spring.components.CustomInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomConfiguration implements WebMvcConfigurer {

    @Autowired
    CustomInterceptor customInterceptor;

    @Autowired
    CustomInterceptor2 customInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/api/*");

        registry.addInterceptor(customInterceptor2)
                .addPathPatterns("/api/*");
    }


    @Bean
    public FilterRegistrationBean<CustomFilter> getCustomFilter(){
        FilterRegistrationBean<CustomFilter> filterFilterRegistrationBean= new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.setFilter(new CustomFilter());
        filterFilterRegistrationBean.addUrlPatterns("/api/*");
        return filterFilterRegistrationBean;
    }



}
