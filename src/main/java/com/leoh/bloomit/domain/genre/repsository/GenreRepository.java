package com.leoh.bloomit.domain.genre.repsository;

import com.leoh.bloomit.domain.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
