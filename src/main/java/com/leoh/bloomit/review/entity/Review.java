package com.leoh.bloomit.review.entity;

import com.leoh.bloomit.book.entity.Book;
import com.leoh.bloomit.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Book book;

    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Lob
    private String content;

}
