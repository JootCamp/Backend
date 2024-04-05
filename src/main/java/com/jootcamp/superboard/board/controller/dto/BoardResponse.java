package com.jootcamp.superboard.board.controller.dto;

import com.jootcamp.superboard.board.service.dto.Board;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardResponse {
    private long id;
    private String title;
    private String description;

    public static BoardResponse from(Board readBoard) {
        return new BoardResponse(readBoard.getId(), readBoard.getTitle(), readBoard.getDescription());
    }
}
