package com.leoh.bloomit.domain.author.repository;

import com.leoh.bloomit.domain.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
