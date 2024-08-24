package com.jootcamp.superboard.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

import static com.jootcamp.superboard.common.constants.UserConstant.USER_INFO;

@Component
public class LoginCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false); // 세션이 존재하는지 확인하고, 존재하면 세션 객체를 반환하고, 존재하지 않으면 null을 반환하는 용도

            if (session == null || session.getAttribute(USER_INFO) == null) {
                response.sendError(401, "로그인 필요");
                return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
            }

            chain.doFilter(request, response); //다음 필터 진행. 없다면 서블릿 띄우기
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("인증 체크 필터 종료 :" + request.getRequestURI());
        }

    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        if (path.startsWith("/board") || path.startsWith("/post")) {
            return request.getMethod().equals("GET");
        }

        String[] excludePath = {"/login", "/logout", "/css/*", "/signup", "/swagger", "/v3", "/favicon.ico"};

        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

}
