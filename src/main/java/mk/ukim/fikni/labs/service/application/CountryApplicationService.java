package mk.ukim.fikni.labs.service.application;

import mk.ukim.fikni.labs.dto.CreateCountryDto;
import mk.ukim.fikni.labs.dto.DisplayCountryDto;
import mk.ukim.fikni.labs.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    void deleteById(Long id);
}
