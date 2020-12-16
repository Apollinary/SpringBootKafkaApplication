package org.example.dao;

import org.example.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    private String COMMENT_ID_COLUMN = "comment_id";
    private String USER_ID_COLUMN = "user_id";
    private String COMMENT_TEXT_COLUMN = "comment_txt";
    private String COMMENT_TIMESTAMP_COLUMN = "comment_timestamp";
    private String PRODUCT_COLUMN = "product";
    private String FIRSTNAME_COLUMN = "firstname";
    private String LASTNAME_COLUMN = "lastname";
    private String AGE_COLUMN = "age";

    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getString(COMMENT_ID_COLUMN));
        comment.setUserId(rs.getLong(USER_ID_COLUMN));
        comment.setFirstName(rs.getString(FIRSTNAME_COLUMN));
        comment.setLastName(rs.getString(LASTNAME_COLUMN));
        comment.setCommentText(rs.getString(COMMENT_TEXT_COLUMN));
        comment.setTimestamp(rs.getLong(COMMENT_TIMESTAMP_COLUMN));
        comment.setProduct(rs.getString(PRODUCT_COLUMN));
        comment.setAge(rs.getInt(AGE_COLUMN));
        return comment;
    }
}
