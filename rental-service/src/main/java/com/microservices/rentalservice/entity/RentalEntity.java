package com.microservices.rentalservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="rentals")
@NoArgsConstructor
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rentalId;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String borrower;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String createdAt;

    @Builder
    public RentalEntity(
        String rentalId,
        String postId,
        Long price,
        String owner,
        String borrower,
        String startDate,
        String endDate,
        String createdAt
    ) {
        this.rentalId = rentalId;
        this.postId = postId;
        this.price = price;
        this.owner = owner;
        this.borrower = borrower;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }
}
