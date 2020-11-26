package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.topicFrom}")
    private String topicFrom;

    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String>
    adapter(KafkaMessageListenerContainer<String, String> container) {
        KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageDrivenChannelAdapter =
                new KafkaMessageDrivenChannelAdapter<>(container, KafkaMessageDrivenChannelAdapter.ListenerMode.record);
        kafkaMessageDrivenChannelAdapter.setOutputChannel(kafkaChannel());
        return kafkaMessageDrivenChannelAdapter;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> container() throws Exception {
        ContainerProperties properties = new ContainerProperties(topicFrom);
        return new KafkaMessageListenerContainer<>(consumerFactory, properties);
    }

    @Autowired
    ConsumerFactory<String, String> consumerFactory;

    @Bean
    public MessageChannel kafkaChannel() {
        return new DirectChannel();
    }
}