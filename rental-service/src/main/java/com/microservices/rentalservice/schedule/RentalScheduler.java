package com.microservices.rentalservice.schedule;

import com.microservices.rentalservice.entity.RentalEntity;
import com.microservices.rentalservice.repository.RentalRepository;
import com.microservices.rentalservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RentalScheduler {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalScheduler(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Scheduled(cron="0 0 0 * * *")
    public void expireJob() {
        DateUtil dateUtil = new DateUtil();

        Iterable<RentalEntity> rentalList = rentalRepository.findAll();


    }
}
