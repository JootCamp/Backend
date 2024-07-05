package com.jootcamp.superboard.common;


import com.jootcamp.superboard.user.controller.dto.AuthUser;
import com.jootcamp.superboard.user.repository.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터가 조건 맞는지 확인
        return parameter.hasParameterAnnotation(UserInfo.class)
                && parameter.getParameterType().equals(AuthUser.class);
    }

    /*
       @param userId
       @param userEmail
       @param nickname
    */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = Objects.requireNonNull(request).getSession();

        final AuthUser userInfo = (AuthUser) session.getAttribute("userInfo");

        return Optional.of(userInfo).orElseThrow(UserNotFoundException::new);
    }
}
