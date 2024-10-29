package com.leoh.bloomit.post.entity;

import com.leoh.bloomit.board.entity.Board;
import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Board board;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCount;

}
