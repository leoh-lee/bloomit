package com.leoh.bloomit.domain.genre.service;

import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.genre.dto.request.GenreSaveRequest;
import com.leoh.bloomit.domain.genre.dto.response.GenreResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.leoh.bloomit.common.exception.ErrorCode.GENRE_NOT_FOUND;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    @DisplayName("장르를 등록한다.")
    void save() {
        // given
        GenreSaveRequest request = new GenreSaveRequest("로맨스");
        // when
        genreService.save(request);
        GenreResponse genreResponse = genreService.search(1L);
        // then
        assertThat(genreResponse.getId()).isEqualTo(1L);
        assertThat(genreResponse.getName()).isEqualTo("로맨스");
    }

    @Test
    @DisplayName("장르를 검색할 때, 해당하는 장르가 없으면 ResourceNotFoundException 발생")
    void searchFail() {
        // given
        // when // then
        assertThatThrownBy(() -> genreService.search(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(GENRE_NOT_FOUND.getMessage());
    }

}