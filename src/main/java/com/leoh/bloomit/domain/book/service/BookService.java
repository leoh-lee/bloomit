package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.common.exception.ErrorCode;
import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.author.dto.response.AuthorResponse;
import com.leoh.bloomit.domain.book.dto.response.BookDetailResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import com.leoh.bloomit.domain.bookauthor.service.BookAuthorService;
import com.leoh.bloomit.domain.rating.service.RatingService;
import com.leoh.bloomit.domain.review.dto.response.ReviewResponse;
import com.leoh.bloomit.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookAuthorService bookAuthorService;
    private final RatingService ratingService;
    private final ReviewService reviewService;

    public BookDetailResponse searchBookDetail(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.BOOK_NOT_FOUND));

        List<AuthorResponse> authors = bookAuthorService.searchAuthorsByBook(book);

        BigDecimal ratingAvgByBook = ratingService.getRatingAvgByBook(book);
        int ratingCount = ratingService.countByBook(book);

        List<ReviewResponse> reviews = reviewService.searchReviewsByBook(book);

        return BookDetailResponse.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .pages(book.getPages())
                .authors(authors)
                .content(book.getContent())
                .description(book.getDescription())
                .posterUrl(book.getImageUrl())
                .ratingAvg(ratingAvgByBook)
                .ratingCount(ratingCount)
                .reviews(reviews)
                .subTitle(book.getSubTitle())
                .publishedYear(book.getPublishedDate())
                .build();
    }
}
