package com.leoh.bloomit.like.entity;

import com.leoh.bloomit.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Member member;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private String targetType;

}
