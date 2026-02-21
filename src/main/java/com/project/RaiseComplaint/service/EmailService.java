package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;
import com.project.RaiseComplaint.util.EmailTemplateBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final AIEmailFormatter aiEmailFormatter;

    public void sendComplaintEmail(Authority authority, Complaint complaint) {

        String aiDescription =
                aiEmailFormatter.formatComplaint(complaint.getDescription());

        String body = EmailTemplateBuilder
                .buildComplaintRaiseEmail(authority, complaint, aiDescription);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(authority.getEmail());
        message.setSubject("New Civic Complaint | ID: " + complaint.getId());
        message.setText(body);

        mailSender.send(message);
    }
}
