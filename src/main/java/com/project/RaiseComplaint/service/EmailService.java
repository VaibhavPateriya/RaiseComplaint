package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendComplaintEmail(Authority authority, Complaint complaint) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(authority.getEmail());
        message.setSubject("New Civic Complaint - " + complaint.getSubject());

        message.setText(buildEmailBody(authority, complaint));

        mailSender.send(message);
    }

    private String buildEmailBody(Authority authority, Complaint complaint) {

        return """
                Respected %s,

                A new civic complaint has been raised in your jurisdiction.

                Complaint Details:
                -------------------
                Subject: %s
                Description: %s
                Area: %s
                City: %s

                Raised By:
                %s (%s)

                Kindly look into the matter at the earliest.
                
                Regards,
                Civic Complaint Portal
                """.formatted(
               authority.getDesignation(),
               complaint.getSubject(),
               complaint.getDescription(),
               authority.getArea(),
               authority.getCity(),
               complaint.getUser().getName(),
               complaint.getUser().getEmail()
        );
    }
}
