package com.jootcamp.superboard.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class LoginCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        try {
                System.out.println("통과");
                HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("userInfo") == null) {
                    System.out.println("미인증 사용자 요청 {}" + requestURI);
                    //로그인으로 redirect
                    response.sendError(401, "로그인 필요");
                    return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
                }


            chain.doFilter(request, response); //다음 필터 진행. 없다면 서블릿 띄우기
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("인증 체크 필터 종료 :" + requestURI);
        }

    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/login", "/logout", "/css/*", "/signup", "/swagger", "/v3"};
        String path = request.getRequestURI();
        System.out.println("snf " + path);
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

}
