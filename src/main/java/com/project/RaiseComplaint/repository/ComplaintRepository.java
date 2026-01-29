package com.project.RaiseComplaint.repository;

import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.entity.ComplaintStatus;
import com.project.RaiseComplaint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
    long countByAuthority_Area(String area);
    Optional<Complaint> findByIdAndUser(Long id, User user);
    List<Complaint> findByAuthority(Authority authority);

    long countByAuthority(Authority authority);
    long countByAuthorityAndStatus(Authority authority, ComplaintStatus status);
}
