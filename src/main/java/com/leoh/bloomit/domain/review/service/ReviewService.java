package com.leoh.bloomit.domain.review.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.service.BookService;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.service.MemberService;
import com.leoh.bloomit.domain.review.dto.request.ReviewCreateRequest;
import com.leoh.bloomit.domain.review.dto.response.ReviewResponse;
import com.leoh.bloomit.domain.review.entity.Review;
import com.leoh.bloomit.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final MemberService memberService;

    public Long createReview(ReviewCreateRequest request) {
        Member member = memberService.getReferenceById(request.memberId());
        Book book = bookService.getReferenceById(request.bookId());

        Review review = Review.builder()
                .book(book)
                .writer(member)
                .content(request.content())
                .build();

        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }

    public Page<ReviewResponse> searchReviewsByBookId(Long bookId, Pageable pageable) {
        Book book = bookService.getReferenceById(bookId);

        Page<Review> reviewPage = reviewRepository.findAllByBook(book, pageable);

        return reviewPage.map(ReviewResponse::fromEntity);
    }
}
