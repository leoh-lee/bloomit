package com.leoh.bloomit.domain.bookread.entity;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.bookread.enums.BookReadStatus;
import com.leoh.bloomit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookReadStatus bookReadStatus;

}
