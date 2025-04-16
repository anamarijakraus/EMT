package mk.ukim.fikni.labs.model.exceptions;

public class BookingNotAvailableException extends RuntimeException{
    public BookingNotAvailableException(Long id) {
        super(String.format("Booking with id: %d is not available", id));
    }
}
