package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.topicFrom}")
    private String topicFrom;

    @Autowired
    ConsumerFactory<String, String> consumerFactory;

    @Autowired
    MyMessageHandler myMessageHandler;

    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String> adapter() {
        return new KafkaMessageDrivenChannelAdapter<>(container(), KafkaMessageDrivenChannelAdapter.ListenerMode.record);
    }

    @Bean
    public IntegrationFlow topicFlow(KafkaMessageDrivenChannelAdapter<String, String> adapter) {
        return IntegrationFlows
                .from(adapter)
                .handle(myMessageHandler)
                .channel(kafkaChannel())
                .get();
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> container() {
        ContainerProperties properties = new ContainerProperties(topicFrom);
        return new KafkaMessageListenerContainer<>(consumerFactory, properties);
    }

    @Bean
    public MessageChannel kafkaChannel() {
        return new DirectChannel();
    }
}