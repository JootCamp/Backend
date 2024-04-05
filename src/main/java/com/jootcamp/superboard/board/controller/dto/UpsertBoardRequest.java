package com.jootcamp.superboard.board.controller.dto;

import com.jootcamp.superboard.board.service.dto.UpsertBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpsertBoardRequest {
    private String title;
    private String description;

    public UpsertBoard toCreateBoard(String userName) {
        return UpsertBoard.builder()
                .userName(userName)
                .title(title)
                .description(description)
                .build();
    }

    public UpsertBoard toUpdateBoard(Long boardId, String userName) {
        return UpsertBoard.builder()
                .id(boardId)
                .userName(userName)
                .title(title)
                .description(description)
                .build();
    }
}
