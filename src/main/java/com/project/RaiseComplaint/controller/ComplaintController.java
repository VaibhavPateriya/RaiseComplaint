package com.project.RaiseComplaint.controller;

import com.project.RaiseComplaint.dto.ComplaintRequest;
import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ComplaintResponse> raiseComplaint(
            @RequestBody ComplaintRequest request,
            Principal principal
            ) {
        String email = principal.getName();
        return ResponseEntity.ok(complaintService.raiseComplaint(request, email));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ComplaintResponse>> myComplaints(Principal principal) {
        return ResponseEntity.ok(
                complaintService.getMyComplaints(principal.getName())
        );
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<String> resolveComplaint(
            @PathVariable Long id,
            Principal principal
    ) {
        complaintService.resolveComplaint(id, principal.getName());
        return ResponseEntity.ok("Complaint resolved successfully");
    }
}
