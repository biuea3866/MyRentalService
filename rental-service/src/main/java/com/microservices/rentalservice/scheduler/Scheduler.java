package com.microservices.rentalservice.scheduler;

import com.microservices.rentalservice.dto.RentalDto;
import com.microservices.rentalservice.entity.RentalEntity;
import com.microservices.rentalservice.repository.RentalRepository;
import com.microservices.rentalservice.service.RentalService;
import com.microservices.rentalservice.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    private RentalService rentalService;
    private RentalRepository rentalRepository;

    public Scheduler(
        RentalService rentalService,
        RentalRepository rentalRepository
    ) {
        this.rentalService = rentalService;
        this.rentalRepository = rentalRepository;
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void cronJob() {
        List<RentalEntity> rentalList = rentalRepository.findAll();

        for(RentalEntity rental: rentalList) {
            if(DateUtil.matchingDate(rental.getEndDate())) {
                rentalService.expiredRental(rental.getRentalId());
            }
        }

    }
}
