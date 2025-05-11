package com.example.msapplication.config;

import com.example.msapplication.event.ApplicationEvent;
import com.example.msapplication.event.JobApplicationEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("job-application-topic", 2, (short) 1);
    }

    @Bean
    public NewTopic createTopic2(){
        return new NewTopic("application-topic", 2, (short) 1);
    }

}



