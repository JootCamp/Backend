package com.jootcamp.superboard.common.configure;

import com.jootcamp.superboard.common.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilterFilterRegistrationBean() {
        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
