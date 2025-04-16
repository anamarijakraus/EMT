package mk.ukim.fikni.labs.dto;

import mk.ukim.fikni.labs.model.domain.Country;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;

import java.time.LocalDateTime;
import java.util.List;

public record TemporaryReservationDto(Long id,
                                      LocalDateTime dateCreated,
                                      DisplayUserDto user,
                                      List<DisplayBookingDto> bookings
) {

    public static TemporaryReservationDto from(TemporaryReservation temporaryReservation) {
        return new TemporaryReservationDto(
                temporaryReservation.getId(),
                temporaryReservation.getDateCreated(),
                DisplayUserDto.from(temporaryReservation.getUser()),
                DisplayBookingDto.from(temporaryReservation.getBookings())
        );
    }

}
