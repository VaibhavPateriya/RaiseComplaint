package com.project.RaiseComplaint.dto;


import com.project.RaiseComplaint.entity.Designation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComplaintRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    @NotBlank
    private String contact;

    @NotBlank
    private String area;

    @NotBlank
    private String city;

    @NotNull
    private Designation authorityType;
}
