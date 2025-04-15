package mk.ukim.fikni.labs.repository;

import mk.ukim.fikni.labs.model.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
