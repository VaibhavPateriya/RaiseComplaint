package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.LoginRequest;
import com.project.RaiseComplaint.dto.LoginResponse;
import com.project.RaiseComplaint.dto.RegisterRequest;
import com.project.RaiseComplaint.entity.User;
import com.project.RaiseComplaint.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterRequest request){
        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());

        if(existingUser.isPresent()){
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setArea(request.getArea());
        user.setCity(request.getCity());

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
       User user = userRepository.findByEmail(request.getEmail())
               .filter(u -> u.getPassword().equals(request.getPassword()))
               .orElseThrow(() -> new RuntimeException("Invalid Credential"));
       LoginResponse response = new LoginResponse();
       response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setArea(user.getArea());
        response.setCity(user.getCity());

        return response;
    }
}
