DELIMITER $$

CREATE PROCEDURE insert_comments_for_boards()
BEGIN
    DECLARE board_id INT;
    DECLARE max_boards INT;
    DECLARE min_boards INT;

    -- board 테이블에서 최소 ID 값과 최대 ID 값을 가져옴
    SELECT MIN(id) INTO min_boards FROM board;
    SELECT MAX(id) INTO max_boards FROM board;

    -- 최소 ID를 변수로 설정
    SET board_id = min_boards;

    -- board_id가 min_boards에서 max_boards까지 반복
    WHILE board_id <= max_boards DO
            -- 댓글 3개 삽입
            INSERT INTO comment (board_id, content) VALUES (board_id, CONCAT('Comment 1 for board ', board_id));
            INSERT INTO comment (board_id, content) VALUES (board_id, CONCAT('Comment 2 for board ', board_id));
            INSERT INTO comment (board_id, content) VALUES (board_id, CONCAT('Comment 3 for board ', board_id));

            -- 다음 board로 이동
            SET board_id = board_id + 1;
        END WHILE;
END$$

DELIMITER ;

-- 저장 프로시저 실행
CALL insert_comments_for_boards();