package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public Book getReferenceById(Long id) {
        return bookRepository.getReferenceById(id);
    }
}
