package com.leoh.bloomit.domain.genre.service;

import com.leoh.bloomit.common.exception.ErrorCode;
import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.genre.dto.request.GenreSaveRequest;
import com.leoh.bloomit.domain.genre.dto.response.GenreResponse;
import com.leoh.bloomit.domain.genre.entity.Genre;
import com.leoh.bloomit.domain.genre.repsository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public void save(GenreSaveRequest request) {
        Genre genre = Genre.create(request.getName());
        genreRepository.save(genre);
    }

    public GenreResponse search(long genreId) {
        return genreRepository.findById(genreId)
                .map(GenreResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.GENRE_NOT_FOUND));
    }
}
