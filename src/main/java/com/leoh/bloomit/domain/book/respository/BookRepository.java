package com.leoh.bloomit.domain.book.respository;

import com.leoh.bloomit.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
