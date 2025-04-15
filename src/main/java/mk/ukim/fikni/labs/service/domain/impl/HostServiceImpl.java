package mk.ukim.fikni.labs.service.domain.impl;

import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.repository.HostRepository;
import mk.ukim.fikni.labs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }


    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingCategory -> {
            if (host.getName() != null) {
                existingCategory.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingCategory.setSurname(host.getSurname());
            }
            if (host.getCountry() != null) {
                existingCategory.setCountry(host.getCountry());
            }
            return hostRepository.save(existingCategory);
        });
    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.of(hostRepository.save(host));
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
