package com.example.aula.companhiaaereaback.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight number is mandatory")
    @Size(max = 255, message = "Flight number must be less than 255 characters")
    private String flightNumber;

    @NotBlank(message = "Origin is mandatory")
    @Size(max = 255, message = "Origin must be less than 255 characters")
    private String origin;

    @NotBlank(message = "Destination is mandatory")
    @Size(max = 255, message = "Destination must be less than 255 characters")
    private String destination;

    @NotNull(message = "Departure time is mandatory")
    @Future(message = "Departure time must be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalTime;
}