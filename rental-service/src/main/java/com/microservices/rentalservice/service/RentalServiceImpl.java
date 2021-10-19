package com.microservices.rentalservice.service;

import com.microservices.rentalservice.dto.RentalDto;
import com.microservices.rentalservice.entity.RentalEntity;
import com.microservices.rentalservice.repository.RentalRepository;
import com.microservices.rentalservice.status.RentalStatus;
import com.microservices.rentalservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    @Override
    public RentalDto completeRental(String rentalId) {
        log.info("Rental Service's Service Layer :: Call completeRental write Method!");

        RentalEntity rentalEntity = rentalRepository.findByRentalId(rentalId);

        rentalEntity.setStatus(RentalStatus.BEING_RENTAL.name());

        rentalRepository.save(rentalEntity);

        return RentalDto.builder()
                        .rentalId(rentalEntity.getRentalId())
                        .postId(rentalEntity.getPostId())
                        .price(rentalEntity.getPrice())
                        .owner(rentalEntity.getOwner())
                        .borrower(rentalEntity.getBorrower())
                        .startDate(rentalEntity.getStartDate())
                        .endDate(rentalEntity.getEndDate())
                        .status(rentalEntity.getStatus())
                        .createdAt(rentalEntity.getCreatedAt())
                        .build();
    }

    @Transactional
    @Override
    public RentalDto getRentalByRentalId(String rentalId) {
        log.info("Rental Service's Service Layer :: Call getRentalByRentalId Method!");

        RentalEntity rentalEntity = rentalRepository.findByRentalId(rentalId);

        return RentalDto.builder()
                        .rentalId(rentalEntity.getRentalId())
                        .postId(rentalEntity.getPostId())
                        .price(rentalEntity.getPrice())
                        .owner(rentalEntity.getOwner())
                        .borrower(rentalEntity.getBorrower())
                        .startDate(rentalEntity.getStartDate())
                        .endDate(rentalEntity.getEndDate())
                        .status(rentalEntity.getStatus())
                        .createdAt(rentalEntity.getCreatedAt())
                        .build();
    }

    @Transactional
    @Override
    public Iterable<RentalDto> getRentalsByOwner(String owner) {
        log.info("Rental Service's Service Layer :: Call getRentalsByOwner Method!");

        Iterable<RentalEntity> rentals = rentalRepository.findAllByOwner(owner);
        List<RentalDto> rentalList = new ArrayList<>();

        rentals.forEach(v -> {
            rentalList.add(RentalDto.builder()
                                    .rentalId(v.getRentalId())
                                    .postId(v.getPostId())
                                    .price(v.getPrice())
                                    .owner(v.getOwner())
                                    .borrower(v.getBorrower())
                                    .startDate(v.getStartDate())
                                    .endDate(v.getEndDate())
                                    .status(v.getStatus())
                                    .createdAt(v.getCreatedAt())
                                    .build());
        });

        return rentalList;
    }

    @Transactional
    @Override
    public Iterable<RentalDto> getRentalsByBorrower(String borrower) {
        log.info("Rental Service's Service Layer :: Call getRentalsByBorrower Method!");

        Iterable<RentalEntity> rentals = rentalRepository.findAllByBorrower(borrower);
        List<RentalDto> rentalList = new ArrayList<>();

        rentals.forEach(v -> {
            rentalList.add(RentalDto.builder()
                                    .rentalId(v.getRentalId())
                                    .postId(v.getPostId())
                                    .price(v.getPrice())
                                    .owner(v.getOwner())
                                    .borrower(v.getBorrower())
                                    .startDate(v.getStartDate())
                                    .endDate(v.getEndDate())
                                    .status(v.getStatus())
                                    .createdAt(v.getCreatedAt())
                                    .build());
        });

        return rentalList;
    }

    @Transactional
    @Override
    public void decline(String rentalId) {
        log.info("Rental Service's Service Layer :: Call decline write Method!");

        RentalEntity rentalEntity = rentalRepository.findByRentalId(rentalId);

        rentalRepository.delete(rentalEntity);
    }

    @Transactional
    @Override
    public Iterable<RentalDto> getRentalsByPending(String owner) {
        log.info("Rental Service's Service Layer :: Call getRentalsByPending write Method!");

        Iterable<RentalEntity> rentals = rentalRepository.findRentalsByPending(owner);
        List<RentalDto> rentalList = new ArrayList<>();

        rentals.forEach(v -> {
            rentalList.add(RentalDto.builder()
                                    .rentalId(v.getRentalId())
                                    .postId(v.getPostId())
                                    .price(v.getPrice())
                                    .owner(v.getOwner())
                                    .borrower(v.getBorrower())
                                    .startDate(v.getStartDate())
                                    .endDate(v.getEndDate())
                                    .status(v.getStatus())
                                    .createdAt(v.getCreatedAt())
                                    .build());
        });

        return rentalList;
    }

    @Transactional
    @Override
    public void expiredRental(String rentalId) {
        log.info("Rental Service's Service Layer :: Call expiredRental write Method!");

        RentalEntity entity = rentalRepository.findByRentalId(rentalId);

        entity.setStatus(RentalStatus.EXPIRED_RENTAL.name());

        rentalRepository.save(entity);
    }
}
