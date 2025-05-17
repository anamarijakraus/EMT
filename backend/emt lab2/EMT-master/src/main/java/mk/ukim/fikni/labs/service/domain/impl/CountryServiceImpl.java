package mk.ukim.fikni.labs.service.domain.impl;

import mk.ukim.fikni.labs.model.domain.Country;
import mk.ukim.fikni.labs.repository.CountryRepository;
import mk.ukim.fikni.labs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id).map(existingCategory -> {
            if (country.getName() != null) {
                existingCategory.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCategory.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCategory);
        });

    }

    // nema vrski zato direkt save
    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
