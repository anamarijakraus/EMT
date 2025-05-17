package mk.ukim.fikni.labs.repository;


import mk.ukim.fikni.labs.model.views.HostCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;


@Repository
public interface HostCountViewRepository extends JpaRepository<HostCountView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW host_count_by_country", nativeQuery = true)
    void refreshMaterializedView();
}
