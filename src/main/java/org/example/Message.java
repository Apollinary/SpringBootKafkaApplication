package org.example;

import java.util.Objects;

public class Message {
    private String messageId;
    private long userId;
    private String messageText;
    private long timestamp;
    private String device;

    public Message() {
    }

    public Message(String messageId, long userId, String messageText) {
        this.messageId = messageId;
        this.userId = userId;
        this.messageText = messageText;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timestamp == message.timestamp &&
                Objects.equals(messageId, message.messageId) &&
                Objects.equals(userId, message.userId) &&
                Objects.equals(messageText, message.messageText) &&
                Objects.equals(device, message.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, userId, messageText, timestamp, device);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", userId='" + userId + '\'' +
                ", messageText='" + messageText + '\'' +
                ", timestamp=" + timestamp +
                ", device='" + device + '\'' +
                '}';
    }
}
