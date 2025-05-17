package mk.ukim.fikni.labs.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import mk.ukim.fikni.labs.model.enumerations.BookingCategory;


@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private BookingCategory category;

    // Booking: M  - Host: 1
    @ManyToOne
    private Host host;
    private Integer numRooms;

    public Booking() {
    }

    public Booking(String name, BookingCategory category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

}
