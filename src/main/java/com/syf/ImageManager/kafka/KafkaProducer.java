package com.syf.ImageManager.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syf.ImageManager.model.User;
import com.syf.ImageManager.service.UserService;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    UserService userService;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceJsonMessage(User user) throws JsonProcessingException {
        // Kafka topic to which you want to publish the JSON message
        String cloudTopic = "user.image";

        // JSON message to be sent

        ObjectMapper objectMapper= new ObjectMapper();
        String userImageEvent=objectMapper.writeValueAsString(user);
        
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(cloudTopic, String.valueOf(user.getId()), userImageEvent);
        System.out.println("Publishing on Kafka Topic user.image "+record.toString()+"  Value : "+record.value());
        
        // Publish JSON message to Kafka topic
        kafkaTemplate.send(record);
    }
}