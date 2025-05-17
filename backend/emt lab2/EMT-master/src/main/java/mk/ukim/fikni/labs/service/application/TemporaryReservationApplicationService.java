package mk.ukim.fikni.labs.service.application;

import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.dto.TemporaryReservationDto;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<DisplayBookingDto> listAllBookingsInList(Long listId);
    Optional<TemporaryReservationDto> getActiveTemporaryList(String username);
    Optional<TemporaryReservationDto> addBookingToTemporaryList(String username, Long bookingId);
    Optional<TemporaryReservationDto> confirmReservation(Long listId);
}
