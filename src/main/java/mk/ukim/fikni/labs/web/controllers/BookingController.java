package mk.ukim.fikni.labs.web.controllers;



import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.fikni.labs.dto.CreateBookingDto;
import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.model.enumerations.BookingCategory;
import mk.ukim.fikni.labs.model.views.AccommodationCountView;
import mk.ukim.fikni.labs.repository.AccommodationCountViewRepository;
import mk.ukim.fikni.labs.service.application.BookingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingApplicationService bookingService;
    private final AccommodationCountViewRepository accommodationCountViewRepository;

    public BookingController(BookingApplicationService bookingService, AccommodationCountViewRepository accommodationCountViewRepository) {
        this.bookingService = bookingService;
        this.accommodationCountViewRepository = accommodationCountViewRepository;
    }


    @Operation(summary = "Get all bookings", description = "Retrieves a list of all bookings.")
    @GetMapping
    public List<DisplayBookingDto> findAll() {
        return bookingService.findAll();
    }

    @Operation(summary = "Get a booking by ID", description = "Finds a booking by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookingDto> findById(@PathVariable Long id) {
        return bookingService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Update an existing booking",
            description = "Updates an existing booking based on the given CreateBookingDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookingDto> update(@PathVariable Long id, @RequestBody CreateBookingDto booking) {
        return bookingService.update(id, booking)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Add a new booking",
            description = "Creates a new booking based on the given CreateBookingDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayBookingDto> save(@RequestBody CreateBookingDto booking) {
        return bookingService.save(booking)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a booking", description = "Deletes a booking by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookingService.findById(id).isPresent()) {
            bookingService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Rent a booking", description = "Rents a booking by its ID.")
    @PatchMapping("/{id}/rent")
    public ResponseEntity<?> rent(@PathVariable Long id) {
        try {
            DisplayBookingDto bookedHousing = bookingService.availableBooking(id).orElseThrow();
            return ResponseEntity.ok(bookedHousing);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return 400 with error message
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<DisplayBookingDto>> searchBookings(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BookingCategory category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Integer numRooms
    ) {
        List<DisplayBookingDto> results = List.of();

        if (name != null) {
            results = bookingService.searchByName(name);
        } else if (category != null) {
            results = bookingService.searchByCategory(category);
        } else if (hostId != null) {
            results = bookingService.searchByHost(hostId);
        } else if (numRooms != null) {
            results = bookingService.searchByNumRooms(numRooms);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(results);
    }


    @GetMapping("/by-host")
    public List<AccommodationCountView> getAccommodationsByHost() {
        return accommodationCountViewRepository.findAll();
    }


}
