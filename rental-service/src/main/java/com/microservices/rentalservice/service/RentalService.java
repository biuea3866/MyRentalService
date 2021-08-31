package com.microservices.rentalservice.service;

import com.microservices.rentalservice.dto.RentalDto;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);

    RentalDto getRentalByRentalId(String rentalId);

    Iterable<RentalDto> getRentalsByOwner(String owner);

    Iterable<RentalDto> getRentalsByBorrower(String borrower);

    RentalDto deleteRental(String rentalId);
}
