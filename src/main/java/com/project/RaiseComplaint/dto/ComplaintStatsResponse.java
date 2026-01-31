package com.project.RaiseComplaint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplaintStatsResponse {

    private long total;
    private long open;
    private long inProgress;
    private long resolved;
}
