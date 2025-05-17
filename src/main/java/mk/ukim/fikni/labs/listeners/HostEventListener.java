package mk.ukim.fikni.labs.listeners;

import jakarta.transaction.Transactional;
import mk.ukim.fikni.labs.events.HostCreatedEvent;
import mk.ukim.fikni.labs.events.HostDeletedEvent;
import mk.ukim.fikni.labs.events.HostUpdatedEvent;
import mk.ukim.fikni.labs.repository.HostCountViewRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListener {

    private final HostCountViewRepository hostCountViewRepository;

    public HostEventListener(HostCountViewRepository hostCountViewRepository) {
        this.hostCountViewRepository = hostCountViewRepository;
    }

    @EventListener
    @Transactional
    public void onHostCreated(HostCreatedEvent event) {
        hostCountViewRepository.refreshMaterializedView();
    }

    @EventListener
    @Transactional
    public void onHostUpdated(HostUpdatedEvent event) {
        hostCountViewRepository.refreshMaterializedView();
    }

    @EventListener
    @Transactional
    public void onHostDeleted(HostDeletedEvent event) {
        hostCountViewRepository.refreshMaterializedView();
    }
}
