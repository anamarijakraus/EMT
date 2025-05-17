package mk.ukim.fikni.labs.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import jakarta.persistence.Table;


@Data
@Entity
@Immutable // from org.hibernate.annotations.Immutable
//@Table(name = "accommodation_count_by_host")
public class AccommodationCountView {
    @Id
    private Long host_id;

    private String host_name;

    private String host_surname;

    private Long accommodation_count;

}
