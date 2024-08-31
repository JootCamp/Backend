package com.jootcamp.superboard.post.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jootcamp.superboard.board.controller.dto.BoardResponse;
import com.jootcamp.superboard.board.controller.dto.UpsertBoardRequest;
import com.jootcamp.superboard.board.repository.BoardRepository;
import com.jootcamp.superboard.board.repository.entity.BoardEntity;
import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.service.dto.UpsertPost;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostApiControllerTest {

    @Autowired
    PostRepository repository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void BoardSetUp() {
        boardRepository.deleteAll();

        BoardEntity board = BoardEntity.builder()
                .title("유머게시판")
                .description("웃긴 게시글만 모음")
                .userId(2024L)
                .build();
        boardRepository.save(board);
    }

    @Test
    void findAllPost() throws Exception{

        // Given
        final Long boardId = 1L;  // 이 부분에 실제 boardId를 넣어줍니다.
        final String url = String.format("/boards/%d/posts", boardId);
        String title = "게시글";
        String content = "게시글이다";

        final UpsertPost saveData = new UpsertPost(title, content, 2024L, 1L);

        repository.save(saveData.toEntity());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isOk());
        System.out.println(resultActions.andDo(print()));
    }
}