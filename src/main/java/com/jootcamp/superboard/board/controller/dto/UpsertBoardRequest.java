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

    public UpsertBoard toUpsertBoard(long userId) {
        return UpsertBoard.builder()
                .userId(userId)
                .title(title)
                .description(description)
                .build();
    }
}
