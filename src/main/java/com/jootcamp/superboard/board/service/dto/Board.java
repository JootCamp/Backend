package com.jootcamp.superboard.board.service.dto;


import com.jootcamp.superboard.board.repository.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Board {

    private String title;
    private String description;
    private Long id;


    public static Board from(BoardEntity board){
        return Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .build();
    }

}
