package com.leoh.bloomit.domain.review.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByBook(Book book, Pageable pageable);

    List<Review> findAllByBook(Book book);

}
