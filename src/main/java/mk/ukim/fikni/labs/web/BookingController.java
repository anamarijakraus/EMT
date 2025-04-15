package mk.ukim.fikni.labs.web;



import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.fikni.labs.dto.CreateBookingDto;
import mk.ukim.fikni.labs.dto.DisplayBookingDto;
import mk.ukim.fikni.labs.service.application.BookingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingApplicationService bookingService;

    public BookingController(BookingApplicationService bookingService) {
        this.bookingService = bookingService;
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

}
