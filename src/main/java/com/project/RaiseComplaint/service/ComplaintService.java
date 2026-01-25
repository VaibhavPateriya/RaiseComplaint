package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.ComplaintRequest;
import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.entity.User;
import com.project.RaiseComplaint.repository.ComplaintRepository;
import com.project.RaiseComplaint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepository  complaintRepository;
    private final UserRepository userRepository;

    public ComplaintResponse raiseComplaint(ComplaintRequest request, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not fond"));

        Complaint complaint = new Complaint();
        complaint.setSubject(request.getSubject());
        complaint.setDescription(request.getDescription());
        complaint.setUser(user);

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
}
