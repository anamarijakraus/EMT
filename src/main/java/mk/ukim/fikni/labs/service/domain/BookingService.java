package mk.ukim.fikni.labs.service.domain;


import mk.ukim.fikni.labs.model.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<Booking> findAll();
    Optional<Booking> findById(Long id);
    Optional<Booking> update(Long id, Booking booking);

    Optional<Booking> save(Booking booking);

    void deleteById(Long id);

    Optional<Booking> availableBooking(Long bookingID);
}
