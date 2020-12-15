package org.example.dao;

import org.example.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {

    private String MESSAGE_ID_COLUMN = "message_id";
    private String USER_ID_COLUMN = "user_id";
    private String MESSAGE_TEXT_COLUMN = "message_txt";
    private String MESSAGE_TIMESTAMP_COLUMN = "message_timestamp";
    private String DEVICE_COLUMN = "device";

    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setMessageId(rs.getString(MESSAGE_ID_COLUMN));
        message.setUserId(rs.getLong(USER_ID_COLUMN));
        message.setMessageText(rs.getString(MESSAGE_TEXT_COLUMN));
        message.setTimestamp(rs.getLong(MESSAGE_TIMESTAMP_COLUMN));
        message.setDevice(rs.getString(DEVICE_COLUMN));
        return message;
    }
}
