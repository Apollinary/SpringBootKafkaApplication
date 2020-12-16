package org.example.dao;

import org.example.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    private String COMMENT_ID_COLUMN = "comment_id";
    private String USER_ID_COLUMN = "user_id";
    private String COMMENT_TEXT_COLUMN = "comment_txt";
    private String COMMENT_TIMESTAMP_COLUMN = "comment_timestamp";
    private String PRODUCT_COLUMN = "product";

    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getString(COMMENT_ID_COLUMN));
        comment.setUserId(rs.getLong(USER_ID_COLUMN));
        comment.setCommentText(rs.getString(COMMENT_TEXT_COLUMN));
        comment.setTimestamp(rs.getLong(COMMENT_TIMESTAMP_COLUMN));
        comment.setProduct(rs.getString(PRODUCT_COLUMN));
        return comment;
    }
}
