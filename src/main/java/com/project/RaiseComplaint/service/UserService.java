package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.LoginRequest;
import com.project.RaiseComplaint.dto.LoginResponse;
import com.project.RaiseComplaint.dto.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
