package mk.ukim.fikni.labs.dto;

import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.model.enumerations.BookingCategory;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookingDto(
        Long id,
        String name,
        BookingCategory category,
        Long hostID,
        Integer numOfRooms
) {
    public static DisplayBookingDto from(Booking booking) {
        return new DisplayBookingDto(
                booking.getId(),
                booking.getName(),
                booking.getCategory(),
                booking.getHost().getId(),
                booking.getNumRooms()
        );
    }

    public static List<DisplayBookingDto> from(List<Booking> bookings) {
        return bookings.stream().map(DisplayBookingDto::from).collect(Collectors.toList());
    }

    public Booking toBooking(Host host) {
        return new Booking(name, category, host, numOfRooms);
    }
}
