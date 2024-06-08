package com.jootcamp.superboard.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Component
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/login", "/logout","/css/*"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("userId") == null) {
                    System.out.println("미인증 사용자 요청 {}" + requestURI);
                    //로그인으로 redirect
                    httpResponse.sendError(401, "로그인 필요");
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
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
