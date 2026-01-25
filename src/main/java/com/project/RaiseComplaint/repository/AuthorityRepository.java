package com.project.RaiseComplaint.repository;

import com.project.RaiseComplaint.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAreaAndCityAndState(
            String area,
            String city,
            String state);
}
