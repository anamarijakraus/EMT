package mk.ukim.fikni.labs.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemporaryReservationNotFoundException extends RuntimeException {

    public TemporaryReservationNotFoundException(Long id) {
        super(String.format("Temporary Reservation with id: %d was not found", id));
    }
}
