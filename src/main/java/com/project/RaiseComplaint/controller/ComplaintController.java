package com.project.RaiseComplaint.controller;

import com.project.RaiseComplaint.dto.ComplaintRequest;
import com.project.RaiseComplaint.dto.ComplaintResponse;
import com.project.RaiseComplaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
