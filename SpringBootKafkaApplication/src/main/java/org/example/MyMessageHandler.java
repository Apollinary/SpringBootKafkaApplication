package org.example;

import org.example.dao.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MyMessageHandler extends MessageProducerSupport implements MessageHandler {

    public MyMessageHandler() {
        setOutputChannelName("kafkaChannel");
    }

    @Autowired
    UserJDBCTemplate userJDBCTemplate;

    @Override
    public void setOutputChannel(MessageChannel messageChannel) {
        super.setOutputChannel(messageChannel);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        User user = (User) message.getPayload();
        user.setTimestamp(System.currentTimeMillis());
        try {
            userJDBCTemplate.getUserByName(user.getFirstName(), user.getLastName());
        } catch (EmptyResultDataAccessException e) {
            userJDBCTemplate.addUser(user.getFirstName(), user.getLastName());
        }
        sendMessage(MessageBuilder.withPayload(user).build());
    }
}
