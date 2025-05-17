package mk.ukim.fikni.labs.repository;

import mk.ukim.fikni.labs.model.domain.User;
import mk.ukim.fikni.labs.model.domain.TemporaryReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    Optional<TemporaryReservation> findByUser(User user);
}
