package mk.ukim.fikni.labs.events;

import lombok.Getter;
import mk.ukim.fikni.labs.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
@Getter
public class HostUpdatedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public HostUpdatedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}