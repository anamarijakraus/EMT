package mk.ukim.fikni.labs.web.controllers;

import mk.ukim.fikni.labs.model.domain.TemporaryReservation;
import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.fikni.labs.dto.TemporaryReservationDto;
import mk.ukim.fikni.labs.model.domain.User;
import mk.ukim.fikni.labs.service.application.TemporaryReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/temporary-reservation")
@Tag(name = "Temporary Reservation API", description = "Endpoints for managing the list of temporary reservations")
public class TemporaryReservationController {

    private final TemporaryReservationApplicationService temporaryReservationApplicationService;

    public TemporaryReservationController(TemporaryReservationApplicationService temporaryReservationApplicationService) {
        this.temporaryReservationApplicationService = temporaryReservationApplicationService;
    }


    @Operation(
            summary = "Get an existing list of reservations ",
            description = "Retrieves the existing list of reservations for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Temporary Reservation list retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "List of reservations not found")}
    )
    @GetMapping
    public ResponseEntity<TemporaryReservationDto> getActiveTemporaryList(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return temporaryReservationApplicationService.getActiveTemporaryList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add booking to Temporary Reservation list",
            description = "Adds a booking to the Temporary Reservation list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Booking added to Temporary Reservation list successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Booking not found")}
    )
    @PostMapping("/add-product/{id}")
    public ResponseEntity<TemporaryReservationDto> addProductToShoppingCart(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return temporaryReservationApplicationService.addBookingToTemporaryList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }




    @Operation(
            summary = "Confirm Temporary Reservation list",
            description = "Marks all bookings in the Temporary Reservation list as rented for the given list ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Temporary Reservation confirmed successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Temporary Reservation list not found"
                    )
            }
    )
    @PostMapping("/confirm/{listId}")
    public ResponseEntity<TemporaryReservationDto> confirmReservation(@PathVariable Long listId) {
        try {
            return temporaryReservationApplicationService.confirmReservation(listId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}

