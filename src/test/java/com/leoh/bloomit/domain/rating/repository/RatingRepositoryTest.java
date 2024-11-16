package com.leoh.bloomit.domain.rating.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.enums.Gender;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import com.leoh.bloomit.domain.rating.entity.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        ratingRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("book으로 평균별점 조회")
    void getRatingAvgByBook() {
        // given
        Book book = saveDummyBook();

        Member member1 = saveDummyMember();
        Member member2 = saveDummyMember();

        Rating rating1 = Rating.builder()
                .member(member1)
                .book(book)
                .rate(7)
                .build();
        Rating rating2 = Rating.builder()
                .member(member2)
                .book(book)
                .rate(6)
                .build();
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        // when

        BigDecimal ratingAvgByBook = ratingRepository.findRatingAvgByBook(book);
        // then
        assertThat(ratingAvgByBook).isEqualTo(BigDecimal.valueOf(6.5));
    }

    @Test
    @DisplayName("책으로 별점 개수를 조회한다.")
    void countByBook() {
        // given
        Book book = saveDummyBook();

        Member member1 = saveDummyMember();
        Member member2 = saveDummyMember();

        Rating rating1 = Rating.builder()
                .member(member1)
                .book(book)
                .rate(7)
                .build();
        Rating rating2 = Rating.builder()
                .member(member2)
                .book(book)
                .rate(6)
                .build();

        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        // when
        int result = ratingRepository.countByBook(book);
        // then
        assertThat(result).isEqualTo(2);
    }

    private Member saveDummyMember() {
        Member member = Member.builder().username("test").name("test").password("password").gender(Gender.MALE).nickname("test").build();
        memberRepository.save(member);
        return member;
    }

    private Book saveDummyBook() {
        Book book = Book.builder()
                .title("test")
                .isbn("test")
                .pages(100)
                .bookAuthors(List.of(BookAuthor.builder().build()))
                .build();
        bookRepository.save(book);
        return book;
    }
}
