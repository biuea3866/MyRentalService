package com.microservices.rentalservice.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.rentalservice.entity.RentalEntity;
import com.microservices.rentalservice.repository.RentalRepository;
import com.microservices.rentalservice.status.RentalStatus;
import com.microservices.rentalservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class KafkaConsumer {
    RentalRepository rentalRepository;

    @Autowired
    public KafkaConsumer(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @KafkaListener(topics="rental-topic")
    public void requestRental(String kafkaMessage) {
        log.info("Kafka Message : " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }

        RentalEntity rentalEntity = RentalEntity.builder()
                                                .rentalId(UUID.randomUUID().toString())
                                                .postId(Long.parseLong(String.valueOf(map.get("postId"))))
                                                .owner((String)map.get("owner"))
                                                .borrower((String)map.get("borrower"))
                                                .price(Long.parseLong(String.valueOf(map.get("price"))))
                                                .startDate((String)map.get("startDate"))
                                                .endDate((String)map.get("endDate"))
                                                .status(RentalStatus.PENDING_RENTAL.name())
                                                .createdAt(DateUtil.dateNow())
                                                .build();

        rentalRepository.save(rentalEntity);
    }
}
