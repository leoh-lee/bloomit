package com.leoh.bloomit.domain.board.service;

import com.leoh.bloomit.domain.board.dto.request.BoardCreateRequest;
import com.leoh.bloomit.domain.board.dto.response.BoardResponse;
import com.leoh.bloomit.domain.board.entity.Board;
import com.leoh.bloomit.domain.board.repository.BoardRepository;
import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import com.leoh.bloomit.domain.boardtype.service.BoardTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardTypeService boardTypeService;

    @Transactional
    public BoardResponse createBoard(BoardCreateRequest boardCreateRequest) {
        String boardTypeName = boardCreateRequest.getBoardType();
        BoardType boardType = boardTypeService.findByName(boardTypeName);

        Board board = boardCreateRequest.toEntity(boardType);
        Board savedBoard = boardRepository.save(board);

        return BoardResponse.fromEntity(savedBoard, boardType);
    }
}
