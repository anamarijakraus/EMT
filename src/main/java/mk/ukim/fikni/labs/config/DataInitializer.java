package mk.ukim.fikni.labs.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.fikni.labs.model.domain.Booking;
import mk.ukim.fikni.labs.model.domain.User;
import mk.ukim.fikni.labs.model.domain.Country;
import mk.ukim.fikni.labs.model.domain.Host;
import mk.ukim.fikni.labs.model.enumerations.BookingCategory;
import mk.ukim.fikni.labs.model.enumerations.Role;
import mk.ukim.fikni.labs.repository.BookingRepository;
import mk.ukim.fikni.labs.repository.CountryRepository;
import mk.ukim.fikni.labs.repository.HostRepository;
import mk.ukim.fikni.labs.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component

public class DataInitializer {
    private final BookingRepository bookingRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(BookingRepository bookingRepository, CountryRepository countryRepository, HostRepository hostRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookingRepository = bookingRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        Country usa = new Country("USA", "North America");
        Country uk = new Country("United Kingdom", "Europe");
        Country germany = new Country("Germany", "Europe");
        countryRepository.saveAll(List.of(usa, uk, germany));

        Host host1 = new Host("Anja", "Ristevska", usa);
        Host host2 = new Host("Sanja", "Saveska", uk);
        Host host3 = new Host("Martin", "Dukovski", germany);
        hostRepository.saveAll(List.of(host1, host2, host3));

        Booking book1 = new Booking("Barbie", BookingCategory.APARTMENT, host1, 50);
        Booking book2 = new Booking("Mia", BookingCategory.HOTEL, host2, 20);
        Booking book3 = new Booking("Aston", BookingCategory.HOUSE, host3, 10);
        bookingRepository.saveAll(List.of(book1, book2, book3));

        userRepository.save(new User(
                "amk",
                passwordEncoder.encode("amk"),
                "AnaMarija",
                "Kraus",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }

}
