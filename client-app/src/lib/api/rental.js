import client from "./client";

export const completeRental = ({
    rentalId,
    acceptance
}) => client.post('/rental-service/complete-rental', {
    rentalId,
    acceptance
});

export const requestRentals = owner => client.get(`/rental-service/${owner}/request-rentals`);