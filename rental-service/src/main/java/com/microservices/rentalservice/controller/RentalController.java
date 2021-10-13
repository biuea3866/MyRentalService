package com.microservices.rentalservice.controller;

import com.microservices.rentalservice.dto.RentalDto;
import com.microservices.rentalservice.service.RentalService;
import com.microservices.rentalservice.vo.RequestComplete;
import com.microservices.rentalservice.vo.RequestCreate;
import com.microservices.rentalservice.vo.ResponseRental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class RentalController {
    private RentalService rentalService;
    private Environment env;

    @Autowired
    public RentalController(
        RentalService rentalService,
        Environment env
    ) {
        this.rentalService = rentalService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format(
            "It's working in Post Service"
                + ", port(local.server.port) =" + env.getProperty("local.server.port")
                + ", port(server.port) =" + env.getProperty("server.port")
        );
    }

    @PostMapping("/complete-rental")
    public ResponseEntity<?> completeRental(@RequestBody RequestComplete vo) {
        log.info("Rental Service's Controller Layer :: Call createRental Method!");

        if(!vo.isAcceptance()) {
            rentalService.decline(vo.getRentalId());

            return ResponseEntity.status(HttpStatus.OK).body("Your request is declined");
        }

        RentalDto rental = rentalService.completeRental(vo.getRentalId());
        ResponseRental responseRental = ResponseRental.builder()
                                                      .rentalId(rental.getRentalId())
                                                      .postId(rental.getPostId())
                                                      .price(rental.getPrice())
                                                      .owner(rental.getOwner())
                                                      .borrower(rental.getBorrower())
                                                      .startDate(rental.getStartDate())
                                                      .endDate(rental.getEndDate())
                                                      .status(rental.getStatus())
                                                      .createdAt(rental.getCreatedAt())
                                                      .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRental);
    }

    @GetMapping("/{rentalId}/rental")
    public ResponseEntity<?> getRentalByRentalId(@PathVariable("rentalId") String rentalId) {
        log.info("Rental Service's Controller Layer :: Call getRentalByRentalId Method!");

        RentalDto rentalDto = rentalService.getRentalByRentalId(rentalId);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseRental.builder()
                                                                       .rentalId(rentalDto.getRentalId())
                                                                       .postId(rentalDto.getPostId())
                                                                       .price(rentalDto.getPrice())
                                                                       .owner(rentalDto.getOwner())
                                                                       .borrower(rentalDto.getBorrower())
                                                                       .startDate(rentalDto.getStartDate())
                                                                       .endDate(rentalDto.getEndDate())
                                                                       .status(rentalDto.getStatus())
                                                                       .createdAt(rentalDto.getCreatedAt())
                                                                       .build());
    }

    @GetMapping("/{owner}/my_rentals")
    public ResponseEntity<?> getRentalsByOwner(@PathVariable("owner") String owner) {
        log.info("Rental Service's Controller Layer :: Call getMyRentalByUserId Method!");

        Iterable<RentalDto> rentalList = rentalService.getRentalsByOwner(owner);
        List<ResponseRental> result = new ArrayList<>();

        rentalList.forEach(v -> {
            result.add(ResponseRental.builder()
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

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{borrower}/borrow_rentals")
    public ResponseEntity<?> getRentalsByBorrower(@PathVariable("borrower") String borrower) {
        log.info("Rental Service's Controller Layer :: Call getBorrowRentalByUserId Method!");

        Iterable<RentalDto> rentalList = rentalService.getRentalsByBorrower(borrower);
        List<ResponseRental> result = new ArrayList<>();

        rentalList.forEach(v -> {
            result.add(ResponseRental.builder()
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

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{owner}/request-rentals")
    public ResponseEntity<?> getRentalsByPending(@PathVariable("owner") String owner) {
        log.info("Rental Service's Controller Layer :: Call getRentalsByPending Method!");

        Iterable<RentalDto> rentalList = rentalService.getRentalsByPending(owner);
        List<ResponseRental> result = new ArrayList<>();

        rentalList.forEach(v -> {
            result.add(ResponseRental.builder()
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

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
