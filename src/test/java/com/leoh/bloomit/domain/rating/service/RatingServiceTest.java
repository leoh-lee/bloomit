package com.leoh.bloomit.domain.rating.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.rating.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingService = new RatingService(ratingRepository);
    }

    @Test
    @DisplayName("별점 평균을 조회한다.")
    void getRatingAvgByBook() {
        // given
        when(ratingRepository.findRatingAvgByBook(any())).thenReturn(BigDecimal.valueOf(6.5));
        // when
        BigDecimal ratingAvgByBook = ratingService.getRatingAvgByBook(Book.builder().build());
        // then
        assertThat(ratingAvgByBook).isEqualTo(BigDecimal.valueOf(3.3));
    }

}
