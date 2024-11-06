package com.leoh.bloomit.domain.board.entity;

import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import com.leoh.bloomit.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_type_id")
    private BoardType boardType;

    private Long relatedId;

    @Builder
    private Board(String name, BoardType boardType, Long relatedId) {
        this.name = name;
        this.boardType = boardType;
        this.relatedId = relatedId;
    }

    public static Board create(String name, BoardType boardType, Long relatedId) {
        return Board.builder()
                .name(name)
                .boardType(boardType)
                .relatedId(relatedId)
                .build();
    }

    public String getBoardTypeName() {
        return boardType.getName();
    }
}
