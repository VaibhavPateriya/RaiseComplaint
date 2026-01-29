package com.project.RaiseComplaint.controller;

import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.dto.ComplaintStatsResponse;
import com.project.RaiseComplaint.service.AdminComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/admin/complaints")
public class AdminComplaintController {

    private final AdminComplaintService adminComplaintService;

    public AdminComplaintController(AdminComplaintService adminComplaintService) {
        this.adminComplaintService = adminComplaintService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> getAreaComplaints(
            Principal principal
    ) {
        return ResponseEntity.ok(
                adminComplaintService.getAreaComplaints(principal.getName())
        );
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComplaintStatsResponse> getStats(
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                adminComplaintService.getComplaintStats(userDetails.getUsername())
        );
    }
}
