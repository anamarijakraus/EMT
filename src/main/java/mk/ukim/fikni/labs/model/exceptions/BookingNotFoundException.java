package mk.ukim.fikni.labs.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(Long id) {
        super(String.format("Booking with id: %d was not found", id));
    }
}
