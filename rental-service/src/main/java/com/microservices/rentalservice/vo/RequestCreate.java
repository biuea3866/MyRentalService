package com.microservices.rentalservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestCreate {
    @NotNull(message="PostId cannot be null")
    private String postId;

    @NotNull(message="Price cannot be null")
    private Long price;

    @NotNull(message="Owner cannot be null")
    private String owner;

    @NotNull(message="Borrower cannot be null")
    private String borrower;

    @NotNull(message="StartDate cannot be null")
    private String startDate;

    @NotNull(message="EndDate cannot be null")
    private String endDate;
}
