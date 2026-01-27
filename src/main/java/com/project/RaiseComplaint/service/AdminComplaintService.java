package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.repository.AuthorityRepository;
import com.project.RaiseComplaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminComplaintService {

    private final AuthorityRepository authorityRepository;
    private final ComplaintRepository complaintRepository;

    public List<ComplaintResponse> getAreaComplaints(String email) {
        Authority authority = authorityRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authority not found"));

        return complaintRepository.findByAuthority(authority)
                .stream()
                .map(c -> new ComplaintResponse(
                        c.getId(),
                        c.getSubject(),
                        c.getStatus().name(),
                        authority.getArea(),
                        authority.getCity(),
                        c.getCreatedAt()
                ))
                .toList();
    }
}
