package com.example.aula.companhiaaereaback.services;

import com.example.aula.companhiaaereaback.models.FlightModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface FlightService {
    Page<FlightModel> findAll(Pageable pageable);
    Page<FlightModel> findAll(Specification<FlightModel> spec, Pageable pageable);
    Optional<FlightModel> findById(Long id);
    FlightModel save(FlightModel flight);
    void deleteById(Long id);
    boolean existsByFlightNumber(String flightNumber);
}