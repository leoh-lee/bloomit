package com.leoh.bloomit.domain.book.controller;

import com.leoh.bloomit.common.dto.ApiResponse;
import com.leoh.bloomit.domain.book.dto.request.BookSaveRequest;
import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "")
    public ResponseEntity<ApiResponse<BookResponse>> createBook(@RequestBody BookSaveRequest bookSaveRequest) {
        BookResponse response = bookService.createBook(bookSaveRequest);
        return ResponseEntity.status(200).body(ApiResponse.success(response));
    }

}
