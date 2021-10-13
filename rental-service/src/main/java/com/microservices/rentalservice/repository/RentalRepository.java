package com.microservices.rentalservice.repository;

import com.microservices.rentalservice.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    RentalEntity findByRentalId(String rentalId);

    Iterable<RentalEntity> findAllByOwner(String owner);

    Iterable<RentalEntity> findAllByBorrower(String borrower);

    @Query(
        value = "SELECT * " +
                "FROM rentals " +
                "WHERE status = 'EXPIRED_RENTAL'",
        nativeQuery = true
    )
    Iterable<RentalEntity> findAllExceptExpired();

    @Query(
        value = "SELECT * " +
                "FROM rentals " +
                "WHERE status = 'PENDING_RENTAL' AND owner = :owner",
        nativeQuery = true
    )
    Iterable<RentalEntity> findRentalsByPending(String owner);
}
