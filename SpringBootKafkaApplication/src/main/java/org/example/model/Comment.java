package org.example.model;

import java.util.Objects;

public class Comment {
    private String commentId;
    private long userId;
    private String firstName;
    private String lastName;
    private String commentText;
    private long timestamp;
    private String product;
    private int age;

    public Comment() {
    }

    public Comment(String commentId, long userId, String commentText) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return userId == comment.userId &&
                timestamp == comment.timestamp &&
                age == comment.age &&
                Objects.equals(commentId, comment.commentId) &&
                Objects.equals(firstName, comment.firstName) &&
                Objects.equals(lastName, comment.lastName) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(product, comment.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, firstName, lastName, commentText, timestamp, product, age);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", timestamp=" + timestamp +
                ", product='" + product + '\'' +
                ", age=" + age +
                '}';
    }
}
