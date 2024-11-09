package com.leoh.bloomit.domain.board.service;

import com.leoh.bloomit.domain.board.dto.request.BoardCreateRequest;
import com.leoh.bloomit.domain.board.dto.response.BoardResponse;
import com.leoh.bloomit.domain.board.entity.Board;
import com.leoh.bloomit.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = boardCreateRequest.toEntity();
        Board savedBoard = boardRepository.save(board);

        return BoardResponse.fromEntity(savedBoard);
    }
}
