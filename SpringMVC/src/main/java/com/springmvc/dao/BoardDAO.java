package com.springmvc.dao;

import com.springmvc.domain.BoardVO;
import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoardDAO
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-13
 */

@Repository
@AllArgsConstructor
public class BoardDAO {

    SqlSessionTemplate sessionTemplate;

    public void insertBoard(BoardVO vo) {
        System.out.println("==> MyBatis로 insertBoard() 기능 처리");
        sessionTemplate.insert("BoardMapper.insertBoard", vo);
    }

    public void updateBoard(BoardVO vo) {
        System.out.println("==> MyBatis로 updateBoard() 기능 처리");
        sessionTemplate.insert("BoardMapper.updateBoard", vo);
    }

    public void deleteBoard(BoardVO vo) {
        System.out.println("==> MyBatis로 deleteBoard() 기능 처리");
        sessionTemplate.insert("BoardMapper.deleteBoard", vo);
    }

    public BoardVO getContent(BoardVO vo) {
        System.out.println("==> MyBatis로 getContent() 기능 처리");
        return sessionTemplate.selectOne("BoardMapper.getContent", vo);
    }

    public List<BoardVO> getBoardList() {
        System.out.println("==> MyBatis로 getBoardList() 기능 처리");
        return sessionTemplate.selectList("BoardMapper.getBoardList");
    }
}
