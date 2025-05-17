package mk.ukim.fikni.labs.service.application.impl;

import mk.ukim.fikni.labs.dto.CreateBookingDto;
import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.model.enumerations.BookingCategory;
import mk.ukim.fikni.labs.service.application.BookingApplicationService;
import mk.ukim.fikni.labs.service.domain.BookingService;
import mk.ukim.fikni.labs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {
    private final BookingService bookingService;
    private final HostService hostService;

    public BookingApplicationServiceImpl(BookingService bookingService, HostService hostService) {
        this.bookingService = bookingService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayBookingDto> findAll() {
        return bookingService.findAll().stream().map(DisplayBookingDto::from).toList();
    }

    @Override
    public Optional<DisplayBookingDto> findById(Long id) {
        return bookingService.findById(id).map(DisplayBookingDto::from);
    }

    @Override
    public Optional<DisplayBookingDto> update(Long id, CreateBookingDto bookingDto) {
        Optional<Host> host = hostService.findById(bookingDto.hostID());
        return bookingService.update(id, bookingDto.toBooking(host.orElse(null))).map(DisplayBookingDto::from);

    }

    @Override
    public Optional<DisplayBookingDto> save(CreateBookingDto bookingDto) {
        Optional<Host> host = hostService.findById(bookingDto.hostID());
        if (host.isPresent()){
            return bookingService.save(bookingDto.toBooking(host.get())).map(DisplayBookingDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        bookingService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookingDto> availableBooking(Long bookingID) {
        return bookingService.availableBooking(bookingID).map(DisplayBookingDto::from);
    }

    @Override
    public List<DisplayBookingDto> searchByName(String name) {
        return bookingService.searchByName(name).stream().map(DisplayBookingDto::from).toList();
    }

    @Override
    public List<DisplayBookingDto> searchByCategory(BookingCategory category) {
        return bookingService.searchByCategory(category).stream().map(DisplayBookingDto::from).toList();
    }

    @Override
    public List<DisplayBookingDto> searchByHost(Long hostId) {
        return bookingService.searchByHost(hostId).stream().map(DisplayBookingDto::from).toList();
    }

    @Override
    public List<DisplayBookingDto> searchByNumRooms(Integer numRooms) {
        return bookingService.searchByNumRooms(numRooms).stream().map(DisplayBookingDto::from).toList();
    }
}
