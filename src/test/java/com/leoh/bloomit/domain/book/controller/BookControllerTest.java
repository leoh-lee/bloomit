package com.leoh.bloomit.domain.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leoh.bloomit.domain.book.dto.request.BookSaveRequest;
import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.service.BookService;
import com.leoh.bloomit.security.WithMockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ActiveProfiles("test")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("책을 하나 등록한다.")
    @WithMockCustomUser
    void save() throws Exception {
        // given
        BookSaveRequest bookSaveRequest = BookSaveRequest
                .builder()
                .title("Effective Java")
                .isbn(UUID.randomUUID().toString())
                .story("굉장히 재밌는 이야기")
                .description("조슈아 작가의 걸작")
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .author("Joshua Bloch")
                .imageUrl("link")
                .build();

        BookResponse bookResponse = BookResponse.fromEntity(bookSaveRequest.toEntity());

        when(bookService.createBook(any())).thenReturn(bookResponse);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .content(objectMapper.writeValueAsString(bookSaveRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}