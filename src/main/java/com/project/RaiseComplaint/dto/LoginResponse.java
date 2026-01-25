package com.project.RaiseComplaint.dto;

import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private String area;
    private String city;
    private String token;
}
