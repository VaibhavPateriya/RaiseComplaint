package com.project.RaiseComplaint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.Message;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIRequest {
    private String model;
    private List<Message> messages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
