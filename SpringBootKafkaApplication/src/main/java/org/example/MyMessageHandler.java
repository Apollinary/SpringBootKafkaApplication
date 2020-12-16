package org.example;

import org.example.dao.UserJDBCTemplate;
import org.example.model.Comment;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MyMessageHandler extends MessageProducerSupport implements MessageHandler {

    private final UserJDBCTemplate userJDBCTemplate;

    @Autowired
    public MyMessageHandler(UserJDBCTemplate userJDBCTemplate) {
        this.userJDBCTemplate = userJDBCTemplate;
        setOutputChannelName("kafkaChannel");
        userJDBCTemplate.initDataBase();
    }

    @Override
    public void setOutputChannel(MessageChannel messageChannel) {
        super.setOutputChannel(messageChannel);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Comment comment = (Comment) message.getPayload();
        comment.setTimestamp(System.currentTimeMillis());

        if (userJDBCTemplate.getUserByName(comment.getFirstName(), comment.getLastName()).size() < 1) {
            userJDBCTemplate.addUser(comment.getFirstName(), comment.getLastName(), comment.getAge());
            sendMessage(MessageBuilder.withPayload(comment).build());
        }

        User user = userJDBCTemplate.getUserByName(comment.getFirstName(),comment.getLastName()).get(0);
        userJDBCTemplate.addComment(Long.parseLong(user.getId()), comment.getCommentText(), comment.getTimestamp(), comment.getProduct());
    }
}
