package com.leoh.bloomit.domain.rating.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public BigDecimal getRatingAvgByBook(Book book) {
        BigDecimal ratingAvgByBook = ratingRepository.findRatingAvgByBook(book);

        return ratingAvgByBook.multiply(BigDecimal.valueOf(5)).divide(BigDecimal.TEN, RoundingMode.HALF_UP);
    }

    public int countByBook(Book book) {
        return ratingRepository.countByBook(book);
    }
}
