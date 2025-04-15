package mk.ukim.fikni.labs.service.domain.impl;



import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.repository.BookingRepository;
import mk.ukim.fikni.labs.repository.HostRepository;
import mk.ukim.fikni.labs.service.domain.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final HostRepository hostRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, HostRepository hostRepository) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Optional<Booking> update(Long id, Booking booking) {
        return bookingRepository.findById(id).map(existingProduct -> {
            if (booking.getName() != null) {
                existingProduct.setName(booking.getName());
            }
            if (booking.getCategory() != null) {
                existingProduct.setCategory(booking.getCategory());
            }
            if (booking.getHost() != null && hostRepository.findById(booking.getHost().getId()).isPresent()) {
                existingProduct.setHost(hostRepository.findById(booking.getHost().getId()).get());
            }
            if (booking.getNumRooms() != null) {
                existingProduct.setNumRooms(booking.getNumRooms());
            }
            return bookingRepository.save(existingProduct);
        });
    }


    //vo if samo za vrskite proverva
    @Override
    public Optional<Booking> save(Booking booking) {
        if (booking.getHost() !=null && hostRepository.findById(booking.getHost().getId()).isPresent())
            return Optional.of(bookingRepository.save(new Booking(booking.getName(), booking.getCategory(), hostRepository.findById(booking.getHost().getId()).get(), booking.getNumRooms())));
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }


    // obelezhi kako iznajmeno
    @Override
    public Optional<Booking> availableBooking(Long bookingID) {
        return bookingRepository.findById(bookingID).map(booking -> {
            if (booking.getNumRooms() > 0) {
                booking.setNumRooms(booking.getNumRooms() - 1);
                bookingRepository.save(booking);
                return booking;
            } else {
                throw new IllegalStateException("No available rooms for this housing.");
            }
        });
    }

}
