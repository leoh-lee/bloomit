package com.leoh.bloomit.domain.like.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.like.enums.LikeTargetType;
import com.leoh.bloomit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LikeTargetType likeTargetType;

}
