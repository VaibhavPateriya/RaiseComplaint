package com.project.RaiseComplaint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ComplaintResponse {
    private Long id;
    private String subject;
    private String status;
    private String authorityName;
    private LocalDateTime createdBy;
}
