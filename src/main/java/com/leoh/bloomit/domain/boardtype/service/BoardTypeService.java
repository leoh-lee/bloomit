package com.leoh.bloomit.domain.boardtype.service;

import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import com.leoh.bloomit.domain.boardtype.repository.BoardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardTypeService {

    private final BoardTypeRepository boardTypeRepository;

    public BoardType findByName(String boardTypeName) {
        return boardTypeRepository.findByName(boardTypeName);
    }

    @Transactional
    public void createBoardType(BoardType boardType) {
        boardTypeRepository.save(boardType);
    }
}
