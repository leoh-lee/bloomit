package com.leoh.bloomit.domain.bookauthor.service;

import com.leoh.bloomit.domain.author.dto.response.AuthorResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import com.leoh.bloomit.domain.bookauthor.repository.BookAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public List<AuthorResponse> searchAuthorsByBook(Book book) {
        return bookAuthorRepository.findAllByBook(book)
                .stream()
                .map(BookAuthor::getAuthor)
                .map(AuthorResponse::fromEntity)
                .toList();
    }
}
