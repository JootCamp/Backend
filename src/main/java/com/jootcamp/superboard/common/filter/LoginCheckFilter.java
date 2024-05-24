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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (!shouldNotFilter(request)) {
                System.out.println("통과");
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("userId") == null) {
                    System.out.println("미인증 사용자 요청 {}" + requestURI);
                    //로그인으로 redirect
<<<<<<< HEAD
                    httpResponse.sendError(401, "로그인 필요");
=======
                    httpResponse.setStatus(401);
>>>>>>> faa3f70 (feat(BE) : #18 OncePerRequestFilter 상속받고 shouldNotFilter 적용)
                    return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
                }
            }
            chain.doFilter(request, response); //다음 필터 진행. 없다면 서블릿 띄우기
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("인증 체크 필터 종료 {}" + requestURI);
        }

    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/", "/login", "/logout","/css/*", "/signup"};
        String path = request.getRequestURI();
        System.out.println("snf "+path);
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

}
