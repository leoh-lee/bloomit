package com.leoh.bloomit.domain.rating.entity;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false, precision = 10, scale = 1)
    private int rate;

}
