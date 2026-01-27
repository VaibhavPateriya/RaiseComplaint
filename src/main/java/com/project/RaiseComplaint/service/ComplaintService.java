package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.ComplaintRequest;
import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.entity.ComplaintStatus;
import com.project.RaiseComplaint.entity.User;
import com.project.RaiseComplaint.repository.AuthorityRepository;
import com.project.RaiseComplaint.repository.ComplaintRepository;
import com.project.RaiseComplaint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepository  complaintRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public ComplaintResponse raiseComplaint(ComplaintRequest request, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not fond"));

        Authority authority = authorityRepository
                .findByAreaAndCity(user.getArea(), user.getCity())
                .orElseThrow(() -> new RuntimeException(
                        "No authority found for area: " + user.getArea()
                ));

        Complaint complaint = new Complaint();
        complaint.setSubject(request.getSubject());
        complaint.setDescription(request.getDescription());
        complaint.setUser(user);
        complaint.setAuthority(authority);
        complaint.setStatus(ComplaintStatus.OPEN);

        Complaint saved = complaintRepository.save(complaint);

        return new ComplaintResponse(
                saved.getId(),
                saved.getSubject(),
                saved.getStatus().name(),
                saved.getUser().getArea(),
                saved.getUser().getCity(),
                saved.getCreatedAt()
        );
    }

    public List<ComplaintResponse> getMyComplaints(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        return complaintRepository.findByUser(user)
                .stream()
                .map(c -> new ComplaintResponse(
                        c.getId(),
                        c.getSubject(),
                        c.getStatus().name(),
                        user.getArea(),
                        user.getCity(),
                        c.getCreatedAt()
                ))
                .toList();
    }

    public void resolveComplaint(Long complaintId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = complaintRepository
                .findByIdAndUser(complaintId, user)
                .orElseThrow(() -> new RuntimeException(
                        "Complaint not found or not authorized"
                ));
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolvedAt(LocalDateTime.now());

        complaintRepository.save(complaint);
    }
}
