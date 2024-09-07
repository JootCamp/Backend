package com.jootcamp.superboard.common.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jootcamp.superboard.board.repository.BoardRepository;
import com.jootcamp.superboard.board.repository.entity.BoardEntity;
import com.jootcamp.superboard.board.repository.exception.BoardNotFoundException;
import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.repository.execption.PostNotFoundException;
import com.jootcamp.superboard.post.service.dto.UpsertPost;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class PostCheckInterceptorTest {
    long boardId, postId;

    @Autowired
    PostRepository repository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUpBoardAndPost() {
        boardRepository.deleteAll();

        BoardEntity board = BoardEntity.builder()
                .title("유머게시판")
                .description("웃긴 게시글만 모음")
                .userId(2024L)
                .build();
        boardRepository.save(board);

        boardId = board.getId();  // 이 부분에 실제 boardId를 넣어줍니다.
        final String url = String.format("/boards/%d/posts", boardId);
        String title = "게시글";
        String content = "게시글이다";

        final UpsertPost saveData = new UpsertPost(title, content, 2024L, 1L);

        postId = repository.save(saveData.toEntity()).getId();
    }

    @AfterEach
    void afterEach() {

    }

    @Test
    @DisplayName("없는 게시글 조회")
    void validPost() throws Exception {

        // Given
        final String url = String.format("/boards/%d/posts/%d", boardId, postId+1);

        // when
        final ResultActions resultActions = mockMvc.perform(get(url));

        // then

        Exception resolvedException = resultActions.andReturn().getResolvedException();
        System.out.println("결과 나와라!!!!!!"+resolvedException);

        System.out.println("결과 나와라!!!!!! 상태 코드: " + resultActions.andReturn().getResponse().getStatus());

        resultActions.andExpect(result ->
                Assertions.assertTrue(result.getResolvedException() instanceof PostNotFoundException)
        );

    }

    @Test
    @DisplayName("없는 게시판 조회")
    void validBoard() throws Exception {

        // Given
        final String url = String.format("/boards/%d/posts/%d", boardId+1, postId);

        // when
        final ResultActions resultActions = mockMvc.perform(get(url));

        // then
        Exception resolvedException = resultActions.andReturn().getResolvedException();
        System.out.println("결과 나와라!!!!!!"+resolvedException);

        System.out.println("결과 나와라!!!!!! 상태 코드: " + resultActions.andReturn().getResponse().getStatus());


        resultActions.andExpect(result ->
                Assertions.assertTrue(result.getResolvedException() instanceof BoardNotFoundException)
        );

    }




}
