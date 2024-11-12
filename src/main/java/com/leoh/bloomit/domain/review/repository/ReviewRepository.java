package com.leoh.bloomit.domain.review.repository;

import com.leoh.bloomit.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
