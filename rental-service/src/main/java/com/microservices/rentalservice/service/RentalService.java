package com.microservices.rentalservice.service;

import com.microservices.rentalservice.dto.RentalDto;

public interface RentalService {
    RentalDto completeRental(String rentalId);

    RentalDto getRentalByRentalId(String rentalId);

    Iterable<RentalDto> getRentalsByOwner(String owner);

    Iterable<RentalDto> getRentalsByBorrower(String borrower);

    void decline(String rentalId);

    Iterable<RentalDto> getRentalsByPending(String owner);

    void expiredRental(String rentalId);
}
