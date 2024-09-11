package com.example.querydsl;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findAll() {
        QBoard board = QBoard.board;
        QComment comment = QComment.comment;

        List<Board> boards = queryFactory
                .selectFrom(board)
                .join(board.comments, comment)
                .distinct()
                .offset(0)
                .limit(10)
                .fetch();

        System.out.println(boards.size());
        System.out.println(boards.get(0).getComments().size());

        // 1단계: 고유한 Board ID를 가져옴 (댓글이 중복되지 않도록 distinct() 사용)
        List<Long> boardIds =                         queryFactory
                .select(board.id)
                .from(board)
                .leftJoin(board.comments, comment)
                .where(comment.content.contains("for"))
                .distinct()
//                .groupBy(board.id)
                .offset(0)
                .limit(10)
                .fetch();

        // 2단계: 해당 Board ID들을 기준으로 실제 데이터를 페치 조인
        List<Board> boards2 = queryFactory
                .selectFrom(board)
                .leftJoin(board.comments, comment).fetchJoin()
                .where(board.id.in(boardIds))
                .fetch();

        System.out.println(boards2.size());

        return boards;
    }

}
