package com.project.RaiseComplaint.repository;

import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
    long countByAuthority_Area(String area);
}
