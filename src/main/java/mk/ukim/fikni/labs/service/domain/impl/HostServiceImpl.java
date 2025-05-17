package mk.ukim.fikni.labs.service.domain.impl;

import mk.ukim.fikni.labs.events.HostUpdatedEvent;
import mk.ukim.fikni.labs.events.HostCreatedEvent;
import mk.ukim.fikni.labs.events.HostDeletedEvent;
import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.repository.HostRepository;
import mk.ukim.fikni.labs.service.domain.HostService;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher eventPublisher;

    public HostServiceImpl(HostRepository hostRepository, ApplicationEventPublisher eventPublisher) {
        this.hostRepository = hostRepository;
        this.eventPublisher = eventPublisher;
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
        return hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null) {
                existingHost.setCountry(host.getCountry());
            }
            Host updatedHost = hostRepository.save(existingHost);
            eventPublisher.publishEvent(new HostUpdatedEvent(updatedHost)); // Emit update event
            return updatedHost;
        });
    }

    @Override
    public Optional<Host> save(Host host) {
        Host saved = hostRepository.save(host);
        eventPublisher.publishEvent(new HostCreatedEvent(saved)); //  Emit create event
        return Optional.of(saved);
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.findById(id).ifPresent(host -> {
            hostRepository.deleteById(id);
            eventPublisher.publishEvent(new HostDeletedEvent(host)); // Emit delete event
        });
    }
}
