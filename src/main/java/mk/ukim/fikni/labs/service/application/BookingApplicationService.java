package mk.ukim.fikni.labs.service.application;

import mk.ukim.fikni.labs.dto.CreateBookingDto;
import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.model.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingApplicationService {
    List<DisplayBookingDto> findAll();
    Optional<DisplayBookingDto> findById(Long id);
    Optional<DisplayBookingDto> update(Long id, CreateBookingDto bookingDto);

    Optional<DisplayBookingDto> save(CreateBookingDto bookingDto);

    void deleteById(Long id);

    Optional<DisplayBookingDto> availableBooking(Long bookingID);
}
