package com.project.programmers.orders;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.project.programmers.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int review(Review review) {
        int row = jdbcTemplate.update(
                "INSERT INTO reviews(seq,user_seq,product_seq,content) VALUES (null,?,?,?)",
                review.getUserSeq(),
                review.getProductSeq(),
                review.getContent()
        );
        return row;
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .content(rs.getString("content"))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
}
