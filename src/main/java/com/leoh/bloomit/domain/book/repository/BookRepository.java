package com.leoh.bloomit.domain.book.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByIsbn(String isbn);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByPublisherContaining(String publisher);
}
