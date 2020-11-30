package org.example;

import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
class MyMessageHandler extends MessageProducerSupport implements MessageHandler {

    public MyMessageHandler() {
        setOutputChannelName("kafkaChannel");
    }

    @Override
    public void setOutputChannel(MessageChannel messageChannel) {
        super.setOutputChannel(messageChannel);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String data = message.getPayload().toString();
        data = JSONProcessor.addTimeStamp(data);
        sendMessage(MessageBuilder.withPayload(data).build());
    }
}