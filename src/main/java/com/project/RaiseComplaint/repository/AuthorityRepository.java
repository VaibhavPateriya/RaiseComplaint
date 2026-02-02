package com.project.RaiseComplaint.repository;

import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAreaIgnoreCaseAndCityIgnoreCaseAndDesignation(
            String area,
            String city,
            Designation designation);

    Optional<Authority> findByEmail(String email);
}
