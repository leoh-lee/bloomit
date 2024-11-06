package com.leoh.bloomit.domain.board.dto.response;

import com.leoh.bloomit.domain.board.entity.Board;
import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponse {

    private Long id;
    private String name;
    private String boardType;
    private Long relatedId;

    public static BoardResponse fromEntity(Board board, BoardType boardType) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .boardType(boardType.getName())
                .relatedId(board.getRelatedId())
                .build();
    }
}
