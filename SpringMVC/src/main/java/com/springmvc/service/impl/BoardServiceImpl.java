package com.springmvc.service.impl;

import com.springmvc.dao.BoardDAO;
import com.springmvc.domain.BoardVO;
import com.springmvc.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BoardServiceImpl
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-13
 */

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.getBoardList();
    }

    @Override
    public BoardVO getContent(BoardVO vo) {
        return boardDAO.getContent(vo);
    }

    @Override
    public void insertBoard(BoardVO vo) {
        boardDAO.insertBoard(vo);
    }

    @Override
    public void updateBoard(BoardVO vo) {
        boardDAO.updateBoard(vo);
    }

    @Override
    public void deleteBoard(BoardVO vo) {
        boardDAO.deleteBoard(vo);
    }
}
