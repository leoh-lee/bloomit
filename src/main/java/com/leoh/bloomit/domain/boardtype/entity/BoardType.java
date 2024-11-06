package com.leoh.bloomit.domain.boardtype.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Builder
    private BoardType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static BoardType create(String name, String description) {
        return BoardType.builder().name(name).description(description).build();
    }

}
