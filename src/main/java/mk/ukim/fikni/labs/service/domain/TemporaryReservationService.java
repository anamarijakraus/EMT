package mk.ukim.fikni.labs.service.domain;


import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<Booking> listAllBookingsInList(Long listId);

    Optional<TemporaryReservation> getActiveTemporaryList(String username);

    Optional<TemporaryReservation> addBookingToTemporaryList(String username, Long bookingId);
    Optional<TemporaryReservation> confirmReservation(Long listId);

}
