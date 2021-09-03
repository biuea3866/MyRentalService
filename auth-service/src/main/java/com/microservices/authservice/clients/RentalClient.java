package com.microservices.authservice.clients;

import com.microservices.authservice.vo.ResponseRental;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="rental-service")
public interface RentalClient {
    @GetMapping("/{owner}/my_rentals_wrong")
    public List<ResponseRental> getRentalsByOwner(@PathVariable("owner") String owner);

    @GetMapping("/{borrower}/borrow_rentals_wrong")
    public List<ResponseRental> getRentalsByBorrower(@PathVariable("borrower") String borrower);
}
