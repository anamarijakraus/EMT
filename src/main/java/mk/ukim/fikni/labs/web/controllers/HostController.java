package mk.ukim.fikni.labs.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.fikni.labs.dto.CreateHostDto;
import mk.ukim.fikni.labs.dto.DisplayHostDto;
import mk.ukim.fikni.labs.model.views.HostCountView;
import mk.ukim.fikni.labs.repository.HostCountViewRepository;
import mk.ukim.fikni.labs.model.projections.HostNameProjection;
import mk.ukim.fikni.labs.repository.HostRepository;
import mk.ukim.fikni.labs.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;
    private final HostCountViewRepository hostCountViewRepository;
    private final HostRepository hostRepository;

    public HostController(HostApplicationService hostApplicationService, HostCountViewRepository hostCountViewRepository, HostRepository hostRepository) {
        this.hostApplicationService = hostApplicationService;
        this.hostCountViewRepository = hostCountViewRepository;
        this.hostRepository = hostRepository;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all hosts.")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Get a host by ID", description = "Finds a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Update an existing host",
            description = "Updates an existing host based on the given CreateBookingDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
        return hostApplicationService.update(id, hostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Add a new host",
            description = "Creates a new host based on the given CreateBookingDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto hostDto) {
        return hostApplicationService.save(hostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-country")
    public List<HostCountView> getHostCountByCountry() {
        return hostCountViewRepository.findAll();
    }

    @GetMapping("/names")
    public List<HostNameProjection> getHostNames() {
        return hostRepository.findAllBy();
    }
}
