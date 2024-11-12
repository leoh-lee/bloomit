package com.leoh.bloomit.domain.review.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.service.BookService;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.service.MemberService;
import com.leoh.bloomit.domain.review.dto.request.ReviewCreateRequest;
import com.leoh.bloomit.domain.review.entity.Review;
import com.leoh.bloomit.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookService bookService;

    @Mock
    private MemberService memberService;

    @Test
    @DisplayName("리뷰 작성")
    void createReview() {
        // given
        ReviewCreateRequest request = ReviewCreateRequest.builder()
                .bookId(1L)
                .memberId(1L)
                .content("너무 재밌어요.")
                .build();

        when(bookService.getReferenceById(1L)).thenReturn(Book.builder().id(1L).build());
        when(memberService.getReferenceById(1L)).thenReturn(Member.builder().id(1L).build());
        when(reviewRepository.save(any())).thenReturn(Review.builder().id(1L).build());
        ReviewService reviewService = new ReviewService(reviewRepository, bookService, memberService);

        // when
        Long savedId = reviewService.createReview(request);
        // then
        assertThat(savedId).isEqualTo(1L);
    }

}