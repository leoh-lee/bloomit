package com.leoh.bloomit.domain.comment.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.comment.enums.CommentTargetType;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.post.entity.Post;
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
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommentTargetType commentTargetType;

    @Column(nullable = false)
    private Long commentTargetId;

    @Lob
    @Column(nullable = false)
    private String content;

}
