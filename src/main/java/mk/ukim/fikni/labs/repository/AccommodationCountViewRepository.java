package mk.ukim.fikni.labs.repository;

import org.springframework.transaction.annotation.Transactional;
import mk.ukim.fikni.labs.model.views.AccommodationCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccommodationCountViewRepository extends JpaRepository<AccommodationCountView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW accommodation_count_by_host", nativeQuery = true)
    void refreshMaterializedView();
}
