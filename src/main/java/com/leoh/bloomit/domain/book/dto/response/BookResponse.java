package com.leoh.bloomit.domain.book.dto.response;

import com.leoh.bloomit.domain.book.entity.Book;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookResponse {

    private Long id;

    private String isbn;

    private String title;

    private String author;

    private String publisher;

    private LocalDateTime publicationDate;

    private String imageUrl;

    private String story;

    private String description;

    public static BookResponse fromEntity(Book book) {

        return new BookResponse(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishedDate(),
                book.getImageUrl(),
                book.getStory(),
                book.getDescription()
        );

    }

}
