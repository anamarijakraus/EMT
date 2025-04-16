package mk.ukim.fikni.labs.service.application.impl;

import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.dto.TemporaryReservationDto;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;
import mk.ukim.fikni.labs.service.application.TemporaryReservationApplicationService;
import mk.ukim.fikni.labs.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
     private final TemporaryReservationService temporaryReservationService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationService temporaryReservationService) {
        this.temporaryReservationService = temporaryReservationService;
    }

    @Override
    public List<DisplayBookingDto> listAllBookingsInList(Long listId) {
        return DisplayBookingDto.from(temporaryReservationService.listAllBookingsInList(listId));
    }

    @Override
    public Optional<TemporaryReservationDto> getActiveTemporaryList(String username) {
        return temporaryReservationService.getActiveTemporaryList(username).map(TemporaryReservationDto::from);
    }

    @Override
    public Optional<TemporaryReservationDto> addBookingToTemporaryList(String username, Long bookingId) {
        return temporaryReservationService.addBookingToTemporaryList(username, bookingId).map(TemporaryReservationDto::from);
    }

    @Override
    public Optional<TemporaryReservationDto> confirmReservation(Long listId) {
        return temporaryReservationService.confirmReservation(listId).map(TemporaryReservationDto::from);
    }
}
