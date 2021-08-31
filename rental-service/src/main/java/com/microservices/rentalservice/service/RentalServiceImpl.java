package com.microservices.rentalservice.service;

import com.microservices.rentalservice.dto.RentalDto;
import com.microservices.rentalservice.entity.RentalEntity;
import com.microservices.rentalservice.repository.RentalRepository;
import com.microservices.rentalservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public RentalDto createRental(RentalDto rentalDto) {
        log.info("Rental Service's Service Layer :: Call createRental write Method!");

        RentalEntity rentalEntity = RentalEntity.builder()
                                                .rentalId(UUID.randomUUID().toString())
                                                .postId(rentalDto.getPostId())
                                                .price(rentalDto.getPrice())
                                                .owner(rentalDto.getOwner())
                                                .borrower(rentalDto.getBorrower())
                                                .startDate(rentalDto.getStartDate())
                                                .endDate(rentalDto.getEndDate())
                                                .createdAt(DateUtil.dateNow())
                                                .build();

        rentalRepository.save(rentalEntity);

        return RentalDto.builder()
                        .rentalId(rentalEntity.getRentalId())
                        .postId(rentalEntity.getPostId())
                        .price(rentalEntity.getPrice())
                        .owner(rentalEntity.getOwner())
                        .borrower(rentalEntity.getBorrower())
                        .startDate(rentalEntity.getStartDate())
                        .endDate(rentalEntity.getEndDate())
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
                                    .createdAt(v.getCreatedAt())
                                    .build());
        });

        return rentalList;
    }

    @Transactional
    @Override
    public RentalDto deleteRental(String rentalId) {
        log.info("Rental Service's Service Layer :: Call deleteRental Method!");

        RentalEntity rentalEntity = rentalRepository.findByRentalId(rentalId);

        rentalRepository.delete(rentalEntity);

        return RentalDto.builder()
                        .rentalId(rentalEntity.getRentalId())
                        .build();
    }
}
