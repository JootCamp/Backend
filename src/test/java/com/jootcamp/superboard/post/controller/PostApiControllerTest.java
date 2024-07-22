package com.jootcamp.superboard.post.controller;


import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.service.dto.UpsertPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostApiControllerTest {

    @Autowired
    PostRepository repository;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void findAllPost() throws Exception{
        //Given
        final String url = "/posts";
        String title = "게시글";
        String content = "게시글이다";

        final UpsertPost saveData = new UpsertPost(title, content, 2024L);

        repository.save(saveData.toEntity());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isOk());
        System.out.println(resultActions.andDo(print()));
    }
}