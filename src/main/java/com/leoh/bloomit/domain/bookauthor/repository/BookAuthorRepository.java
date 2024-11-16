package com.leoh.bloomit.domain.bookauthor.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    List<BookAuthor> findAllByBook(Book book);
}
