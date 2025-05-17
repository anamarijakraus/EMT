package mk.ukim.fikni.labs.repository;

import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<HostNameProjection> findAllBy();
}
