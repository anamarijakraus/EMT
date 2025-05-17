package mk.ukim.fikni.labs.model.exceptions;

public class BookingAlreadyInListException extends RuntimeException{
    public BookingAlreadyInListException(Long id) {
        super(String.format("Booking with id: %d already exists", id));
    }
}
