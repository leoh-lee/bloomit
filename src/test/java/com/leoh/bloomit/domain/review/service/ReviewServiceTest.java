package com.leoh.bloomit.domain.review.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.service.MemberService;
import com.leoh.bloomit.domain.review.dto.request.ReviewCreateRequest;
import com.leoh.bloomit.domain.review.dto.response.ReviewResponse;
import com.leoh.bloomit.domain.review.entity.Review;
import com.leoh.bloomit.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        reviewService = new ReviewService(reviewRepository, bookRepository, memberService);
    }

    @Test
    @DisplayName("리뷰 작성")
    void createReview() {
        // given
        ReviewCreateRequest request = ReviewCreateRequest.builder()
                .bookId(1L)
                .memberId(1L)
                .content("너무 재밌어요.")
                .build();

        when(bookRepository.getReferenceById(1L)).thenReturn(Book.builder().id(1L).build());
        when(memberService.getReferenceById(1L)).thenReturn(Member.builder().id(1L).build());
        when(reviewRepository.save(any())).thenReturn(Review.builder().id(1L).build());

        // when
        Long savedId = reviewService.createReview(request);
        // then
        assertThat(savedId).isEqualTo(1L);
    }

    @Test
    @DisplayName("도서 ID로 리뷰리스트 조회")
    void getReviewsByBookId() {
        // given
        Book book = Book.builder().id(1L).title("effective Java").build();
        Member member = Member.builder().id(1L).name("leoh").build();
        Page<Review> reviews = new PageImpl<>(List.of(Review.builder().id(1L).book(book).member(member).build()));
        Pageable pageable = PageRequest.of(1, 5);

        when(bookRepository.getReferenceById(1L)).thenReturn(Book.builder().id(1L).build());
        when(reviewRepository.findAllByBook(any(), any())).thenReturn(reviews);

        // when
        Page<ReviewResponse> responses = reviewService.searchReviewsByBookId(book.getId(), pageable);
        // then
        assertThat(responses).isNotNull();
        assertThat(responses.getTotalElements()).isEqualTo(1);
    }

}