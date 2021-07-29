package com.springmvc.service;

import com.springmvc.domain.BoardVO;

import java.util.List;

/**
 * BoardService
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-13
 */
public interface BoardService {
    List<BoardVO> getBoardList();
    BoardVO getContent(BoardVO vo);
    void insertBoard(BoardVO vo);
    void updateBoard(BoardVO vo);
    void deleteBoard(BoardVO vo);
}
