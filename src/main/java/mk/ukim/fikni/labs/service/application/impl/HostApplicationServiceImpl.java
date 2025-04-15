package mk.ukim.fikni.labs.service.application.impl;

import mk.ukim.fikni.labs.dto.CreateHostDto;
import mk.ukim.fikni.labs.dto.DisplayHostDto;
import mk.ukim.fikni.labs.model.domain.Country;
import mk.ukim.fikni.labs.service.application.HostApplicationService;
import mk.ukim.fikni.labs.service.domain.CountryService;
import mk.ukim.fikni.labs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.countryId());
        return hostService.update(id, host.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.countryId());
        if (country.isPresent()){
            return hostService.save(host.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }
}
