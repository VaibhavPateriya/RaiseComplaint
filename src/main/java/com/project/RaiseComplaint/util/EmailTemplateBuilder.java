package com.project.RaiseComplaint.util;

import com.project.RaiseComplaint.entity.Authority;
import com.project.RaiseComplaint.entity.Complaint;

public class EmailTemplateBuilder {
    public static String buildComplaintRaiseEmail (
            Authority authority,
            Complaint complaint,
            String formattedDescription
    ) {
        return String.format(
                """
                Dear %s (%s),

                A new civic complaint has been registered.
                
                Complaint ID   : %d
                Subject        : %s
                
                Description    :
                %s

                Area           : %s
                City           : %s
                State          : %s

                Contact        : %s
                Status         : %s
                        
                Regards,
                Civic Complaint System
                """,
                authority.getName(),
                authority.getDesignation().name(),
                complaint.getId(),
                complaint.getSubject(),
                formattedDescription,
                authority.getArea(),
                authority.getCity(),
                authority.getState(),
                complaint.getContact(),
                complaint.getStatus().name()
        );
    }
}
