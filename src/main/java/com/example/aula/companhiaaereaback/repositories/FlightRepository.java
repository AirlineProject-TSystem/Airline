package com.example.aula.companhiaaereaback.repositories;

import com.example.aula.companhiaaereaback.models.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightModel, Long>, JpaSpecificationExecutor<FlightModel> {
    boolean existsByFlightNumber(String flightNumber);
}