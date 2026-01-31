package com.project.RaiseComplaint.dto;

import com.project.RaiseComplaint.entity.ComplaintStatus;
import lombok.Data;

@Data
public class UpdateComplaintStatusRequest {
    private ComplaintStatus status;
}
