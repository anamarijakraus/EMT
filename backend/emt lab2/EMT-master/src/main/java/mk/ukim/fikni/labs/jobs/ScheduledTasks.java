package mk.ukim.fikni.labs.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mk.ukim.fikni.labs.repository.AccommodationCountViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {
    private final AccommodationCountViewRepository viewRepository;

    public ScheduledTasks(AccommodationCountViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshAccommodationView() {
        viewRepository.refreshMaterializedView();
    }

//    @Scheduled(fixedRate = 60000) // every 60 seconds for testing
//    public void refreshAccommodationView() {
//        System.out.println("Refreshing materialized view...");
//        viewRepository.refreshMaterializedView();
//    }
}

