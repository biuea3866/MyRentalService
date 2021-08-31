package com.microservices.postservice.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.vo.RequestRental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public RequestRental send(
        String topic,
        RequestRental postVo
    ) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString  = "";

        try {
            jsonInString = mapper.writeValueAsString(postVo);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);

        log.info("Kafka Producer sent data from the Post Service: " + postVo);

        return postVo;
    }
}
