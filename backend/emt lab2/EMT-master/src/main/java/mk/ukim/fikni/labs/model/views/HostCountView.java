package mk.ukim.fikni.labs.model.views;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import jakarta.persistence.Table;


@Data
@Entity
@Immutable // from org.hibernate.annotations.Immutable
//@Table(name = "host_count_by_country")
public class HostCountView {
    @Id
    @Column(name = "country_id")
    private Long country_id;

    @Column(name = "country_name")
    private String country_name;

    @Column(name = "host_count")
    private Long host_count;
}


