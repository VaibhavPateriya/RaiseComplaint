package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.dto.ComplaintStatsResponse;
import com.project.RaiseComplaint.dto.UpdateComplaintStatusRequest;
import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.entity.ComplaintStatus;
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

    public ComplaintStatsResponse getComplaintStats(String email) {
        Authority authority = authorityRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authority not found"));

        long total = complaintRepository.countByAuthority(authority);
        long open = complaintRepository.countByAuthorityAndStatus(authority, ComplaintStatus.OPEN);
        long inProgess = complaintRepository.countByAuthorityAndStatus(authority, ComplaintStatus.IN_PROGRESS);
        long resolved = complaintRepository.countByAuthorityAndStatus(authority, ComplaintStatus.RESOLVED);

        return new ComplaintStatsResponse(total, open, inProgess, resolved);
    }

    public void updateComplaintStatus(
            Long complaintId,
            UpdateComplaintStatusRequest request,
            String email
    ) {
        Authority authority = authorityRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authority not found"));

        Complaint complaint = complaintRepository
                .findByIdAndAuthority(complaintId, authority)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(request.getStatus());
        complaintRepository.save(complaint);
    }
}
