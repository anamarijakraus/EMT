package mk.ukim.fikni.labs.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.fikni.labs.model.domain.Country;


@Data
@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne
    private Country country;

    public Host() {
    }

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
