package com.leoh.bloomit.domain.board.entity;

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

    @Builder
    private Board(String name) {
        this.name = name;
    }

    public static Board create(String name) {
        return Board.builder()
                .name(name)
                .build();
    }

}
