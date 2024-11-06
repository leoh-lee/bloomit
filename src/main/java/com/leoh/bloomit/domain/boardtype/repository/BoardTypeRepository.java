package com.leoh.bloomit.domain.boardtype.repository;

import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {
    BoardType findByName(String name);
}
