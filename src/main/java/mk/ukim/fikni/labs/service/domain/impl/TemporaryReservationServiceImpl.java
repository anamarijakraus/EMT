package mk.ukim.fikni.labs.service.domain.impl;

import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;
import mk.ukim.fikni.labs.model.domain.User;
import mk.ukim.fikni.labs.model.exceptions.BookingAlreadyInListException;
import mk.ukim.fikni.labs.model.exceptions.BookingNotAvailableException;
import mk.ukim.fikni.labs.model.exceptions.BookingNotFoundException;
import mk.ukim.fikni.labs.model.exceptions.TemporaryReservationNotFoundException;
import mk.ukim.fikni.labs.repository.TemporaryReservationRepository;
import mk.ukim.fikni.labs.service.domain.BookingService;
import mk.ukim.fikni.labs.service.domain.TemporaryReservationService;
import mk.ukim.fikni.labs.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final BookingService bookingService;
    private final UserService userService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, BookingService bookingService, UserService userService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.bookingService = bookingService;
        this.userService = userService;
    }


    @Override
    public List<Booking> listAllBookingsInList(Long listId) {
        if (temporaryReservationRepository.findById(listId).isEmpty())
            throw new TemporaryReservationNotFoundException(listId);
        return temporaryReservationRepository.findById(listId).get().getBookings();
    }

    @Override
    public Optional<TemporaryReservation> getActiveTemporaryList(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(temporaryReservationRepository.findByUser(user)
                        .orElseGet(() -> temporaryReservationRepository.save(new TemporaryReservation(user))));

    }

    @Override
    public Optional<TemporaryReservation> addBookingToTemporaryList(String username, Long bookingId) {

        if (getActiveTemporaryList(username).isPresent()) {
            TemporaryReservation tempReservation = getActiveTemporaryList(username).get();


            Booking booking = bookingService.findById(bookingId)
                    .orElseThrow(() -> new BookingNotFoundException(bookingId));

            if (booking.getIsRented()) {
                throw new BookingNotAvailableException(bookingId);
            }

            if (tempReservation.getBookings().stream().anyMatch(b -> b.getId().equals(bookingId))) {
                throw new BookingAlreadyInListException(bookingId);
            }

            tempReservation.getBookings().add(booking);
            return Optional.of(temporaryReservationRepository.save(tempReservation));
        }
        return Optional.empty();
    }


    @Override
    public  Optional<TemporaryReservation> confirmReservation(Long listId) {
        TemporaryReservation reservation = temporaryReservationRepository.findById(listId)
                .orElseThrow(() -> new TemporaryReservationNotFoundException(listId));

        reservation.getBookings().forEach(booking -> booking.setIsRented(true));
        reservation.getBookings().forEach(booking -> booking.setNumRooms(0));

        return Optional.of(temporaryReservationRepository.save(reservation));
    }


}

