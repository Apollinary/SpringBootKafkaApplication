package org.example;

import org.example.dao.UserJDBCTemplate;
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

    UserJDBCTemplate userJDBCTemplate;

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
        User user = (User) message.getPayload();
        user.setTimestamp(System.currentTimeMillis());
        if (userJDBCTemplate.getUserByName(user.getFirstName(), user.getLastName()).size() < 1) {
            userJDBCTemplate.addUser(user.getFirstName(), user.getLastName(), user.getAge());
            sendMessage(MessageBuilder.withPayload(user).build());
        }
        user.setId(userJDBCTemplate.getUserByName(user.getFirstName(),user.getLastName()).get(0).getId());
        userJDBCTemplate.addMessage(Long.parseLong(user.getId()), user.getMessageText(), user.getTimestamp(), user.getDevice());
    }
}
