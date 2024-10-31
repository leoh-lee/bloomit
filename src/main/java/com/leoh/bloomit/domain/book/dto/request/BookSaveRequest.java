package com.leoh.bloomit.domain.book.dto.request;

import com.leoh.bloomit.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime publishedDate;
    private String imageUrl;
    private String story;
    private String description;

    public Book toEntity() {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publisher(publisher)
                .publishedDate(publishedDate)
                .imageUrl(imageUrl)
                .story(story)
                .description(description)
                .build();
    }

}
