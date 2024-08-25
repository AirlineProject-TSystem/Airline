package com.example.aula.companhiaaereaback.controllers;

import com.example.aula.companhiaaereaback.models.FlightModel;
import com.example.aula.companhiaaereaback.services.FlightService;
import com.example.aula.companhiaaereaback.specifications.FlightSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
@Validated
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public Page<FlightModel> getAllFlights(Pageable pageable) {
        return flightService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightModel> getFlightById(@PathVariable Long id) {
        Optional<FlightModel> flight = flightService.findById(id);
        return flight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightModel flight) {
        if (flightService.existsByFlightNumber(flight.getFlightNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight number already exists");
        }
        FlightModel savedFlight = flightService.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @Valid @RequestBody FlightModel flightDetails) {
        Optional<FlightModel> flight = flightService.findById(id);
        if (flight.isPresent()) {
            FlightModel updatedFlight = flight.get();
            updatedFlight.setFlightNumber(flightDetails.getFlightNumber());
            updatedFlight.setOrigin(flightDetails.getOrigin());
            updatedFlight.setDestination(flightDetails.getDestination());
            updatedFlight.setDepartureTime(flightDetails.getDepartureTime());
            updatedFlight.setArrivalTime(flightDetails.getArrivalTime());
            return ResponseEntity.ok(flightService.save(updatedFlight));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public Page<FlightModel> filterFlights(@RequestParam(required = false) String origin,
                                           @RequestParam(required = false) String destination,
                                           @RequestParam(required = false) LocalDateTime departureTimeStart,
                                           @RequestParam(required = false) LocalDateTime departureTimeEnd,
                                           @RequestParam(required = false) LocalDateTime arrivalTimeStart,
                                           @RequestParam(required = false) LocalDateTime arrivalTimeEnd,
                                           Pageable pageable) {
        Specification<FlightModel> spec = Specification.where(FlightSpecification.hasOrigin(origin))
                .and(FlightSpecification.hasDestination(destination))
                .and(FlightSpecification.hasDepartureTimeBetween(departureTimeStart, departureTimeEnd))
                .and(FlightSpecification.hasArrivalTimeBetween(arrivalTimeStart, arrivalTimeEnd));
        return flightService.findAll(spec, pageable);
    }
}