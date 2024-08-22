package com.jootcamp.superboard.common.intercepter;

import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.repository.execption.PostNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class PostCheckInterceptor implements HandlerInterceptor {

    private final PostRepository postRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String pattern = "/boards/(\\d+)/posts/(\\d+)/comments(/(\\d+)?)?";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(uri);


        if(matcher.find()) {
            Long boardId = Long.parseLong(matcher.group(1));
            Long postId = Long.parseLong(matcher.group(2));
            Long commentId = matcher.group(4) != null ? Long.parseLong(matcher.group(4)) : null;

            postRepository.findByIdAndIsDeletedIsFalse(postId)
                    .orElseThrow(()-> new PostNotFoundException(postId));
        }

        return true;
    }
}
