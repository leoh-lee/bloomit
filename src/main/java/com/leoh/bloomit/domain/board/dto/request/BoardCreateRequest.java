package com.leoh.bloomit.domain.board.dto.request;

import com.leoh.bloomit.domain.board.entity.Board;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoardCreateRequest {

    private Long id;
    private String name;

    public Board toEntity() {
        return Board.builder()
                .name(name)
                .build();
    }
}
