package mk.ukim.fikni.labs.repository;

import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.model.enumerations.BookingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByNameContainingIgnoreCase(String name);
    List<Booking> findByCategory(BookingCategory category);
    List<Booking> findByHost_NameContainingIgnoreCaseOrHost_SurnameContainingIgnoreCase(String name, String surname);
    List<Booking> findByNumRooms(Integer numRooms);
}
