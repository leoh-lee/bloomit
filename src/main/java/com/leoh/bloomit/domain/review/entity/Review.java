package com.leoh.bloomit.domain.review.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    private String content;

    public Long getBookId() {
        return book.getId();
    }

    public String getBookTitle() {
        return book.getTitle();
    }

    public Long getMemberId() {
        return member.getId();
    }

    public String getMemberName() {
        return member.getName();
    }

}
