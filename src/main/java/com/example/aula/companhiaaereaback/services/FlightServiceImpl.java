package com.example.aula.companhiaaereaback.services;

import com.example.aula.companhiaaereaback.models.FlightModel;
import com.example.aula.companhiaaereaback.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Page<FlightModel> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Override
    public Page<FlightModel> findAll(Specification<FlightModel> spec, Pageable pageable) {
        return flightRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<FlightModel> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public FlightModel save(FlightModel flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public boolean existsByFlightNumber(String flightNumber) {
        return flightRepository.existsByFlightNumber(flightNumber);
    }
}