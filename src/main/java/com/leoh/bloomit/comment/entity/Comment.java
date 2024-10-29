package com.leoh.bloomit.comment.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.member.entity.Member;
import com.leoh.bloomit.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Post post;

    @Lob
    @Column(nullable = false)
    private String content;

}
