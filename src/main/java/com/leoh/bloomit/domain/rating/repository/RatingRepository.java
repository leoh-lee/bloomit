package com.leoh.bloomit.domain.rating.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select avg(r.rate) from Rating r where r.book = :book")
    BigDecimal findRatingAvgByBook(@Param("book") Book book);

    int countByBook(Book book);

}
