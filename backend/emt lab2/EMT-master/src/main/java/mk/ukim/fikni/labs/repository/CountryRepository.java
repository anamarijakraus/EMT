package mk.ukim.fikni.labs.repository;

import mk.ukim.fikni.labs.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
