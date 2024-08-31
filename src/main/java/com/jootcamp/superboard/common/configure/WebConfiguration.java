package com.jootcamp.superboard.common.configure;

import com.jootcamp.superboard.common.UserArgumentResolver;
import com.jootcamp.superboard.common.filter.LoginCheckFilter;
import com.jootcamp.superboard.common.intercepter.PostCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final PostCheckInterceptor postCheckInterceptor;

    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilterFilterRegistrationBean() {
        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*") // 허용할 HTTP 메서드 지정
                .allowedHeaders("*") // 허용할 헤더 지정
                .allowCredentials(true); // 자격 증명 허용 여부
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(postCheckInterceptor)
                .addPathPatterns("/boards/{boardId}/posts/{postId}/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver()); // 유저정보 가져와서 바인딩 할 수 있도록 등록
    }
}
