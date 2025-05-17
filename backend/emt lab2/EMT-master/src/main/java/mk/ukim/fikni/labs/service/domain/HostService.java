package mk.ukim.fikni.labs.service.domain;


import mk.ukim.fikni.labs.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> update(Long id, Host host);

    Optional<Host> save(Host host);

    void deleteById(Long id);
}
