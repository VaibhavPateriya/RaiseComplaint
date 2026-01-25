package com.project.RaiseComplaint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComplaintRequest {
    @NotBlank
    private String area;

    @NotBlank
    private String city;

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    private String contact;
}
