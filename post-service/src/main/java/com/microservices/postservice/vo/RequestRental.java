package com.microservices.postservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestRental {
    @NotNull(message="PostId cannot be null")
    Long postId;

    @NotNull(message="Owner cannot be null")
    String owner;

    @NotNull(message="Borrower cannot be null")
    String borrower;

    @NotNull(message="Price cannot be null")
    Long price;

    @NotNull(message="StartDate cannot be null")
    String startDate;

    @NotNull(message="EndDate cannot be null")
    String endDate;
}
