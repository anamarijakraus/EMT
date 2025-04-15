package mk.ukim.fikni.labs.service.application;

import mk.ukim.fikni.labs.dto.CreateHostDto;
import mk.ukim.fikni.labs.dto.DisplayHostDto;
import mk.ukim.fikni.labs.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto host);

    Optional<DisplayHostDto> save(CreateHostDto host);

    void deleteById(Long id);
}
