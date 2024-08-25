package com.example.aula.companhiaaereaback.specifications;

import com.example.aula.companhiaaereaback.models.FlightModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class FlightSpecification {
    public static Specification<FlightModel> hasOrigin(String origin) {
        return (Root<FlightModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (origin == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("origin"), origin);
        };
    }

    public static Specification<FlightModel> hasDestination(String destination) {
        return (Root<FlightModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (destination == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("destination"), destination);
        };
    }

    public static Specification<FlightModel> hasDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (Root<FlightModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (start == null || end == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("departureTime"), start, end);
        };
    }

    public static Specification<FlightModel> hasArrivalTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (Root<FlightModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (start == null || end == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("arrivalTime"), start, end);
        };
    }
}