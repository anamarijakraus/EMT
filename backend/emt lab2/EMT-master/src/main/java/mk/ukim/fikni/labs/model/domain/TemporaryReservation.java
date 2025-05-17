package mk.ukim.fikni.labs.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TemporaryReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Booking> bookings;

    public TemporaryReservation() {
    }

    public TemporaryReservation(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.bookings = new ArrayList<>();
    }
}
