package com.leoh.bloomit.domain.board.service;

import com.leoh.bloomit.domain.board.dto.request.BoardCreateRequest;
import com.leoh.bloomit.domain.board.dto.response.BoardResponse;
import com.leoh.bloomit.domain.boardtype.entity.BoardType;
import com.leoh.bloomit.domain.boardtype.service.BoardTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardTypeService boardTypeService;

    @Test
    @DisplayName("게시판을 하나 생성한다.")
    void createBoard() {
        // given
        String boardName = "boardName";
        String boardTypeName = "토론";
        BoardCreateRequest boardCreateRequest = BoardCreateRequest.builder().name(boardName).boardType(boardTypeName).build();
        BoardType boardType = BoardType.create(boardName, "board Type");
        boardTypeService.createBoardType(boardType);
        // when
        BoardResponse boardResponse = boardService.createBoard(boardCreateRequest);
        // then
        assertThat(boardResponse).isNotNull();
        assertThat(boardResponse.getName()).isEqualTo(boardCreateRequest.getName());
    }

}