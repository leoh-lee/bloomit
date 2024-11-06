package com.leoh.bloomit.domain.board.dto.request;

import com.leoh.bloomit.domain.board.entity.Board;
import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoardCreateRequest {

    private Long id;
    private String name;
    private String boardType;
    private Long relatedId;

    public Board toEntity(BoardType boardType) {
        return Board.builder()
                .name(name)
                .boardType(boardType)
                .relatedId(relatedId)
                .build();
    }
}
