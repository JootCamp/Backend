package com.jootcamp.superboard.common.intercepter;

import com.jootcamp.superboard.board.service.BoardService;
import com.jootcamp.superboard.common.exception.BadRequestException;
import com.jootcamp.superboard.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostCheckInterceptor implements HandlerInterceptor {

    private final BoardService boardService;
    private final PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> pathVariables =
                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (!pathVariables.isEmpty()) {
            try {
                Long boardId = parseLong(pathVariables.get("boardId"));
                Long postId = parseLong(pathVariables.get("postId"));

                // board 안에 post 있는지 확인
                boardService.existsBoard(boardId);
                postService.existsPost(boardId, postId);

            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    private Long parseLong(String value) {
        try {
            return value != null ? Long.parseLong(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

