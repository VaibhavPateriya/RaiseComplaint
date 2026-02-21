package com.project.RaiseComplaint.service;

import com.project.RaiseComplaint.dto.OpenAIRequest;
import com.project.RaiseComplaint.dto.OpenAIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIEmailFormatter {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public String formatComplaint(String rawText) {
        try{
            OpenAIRequest request = new OpenAIRequest(
                    model,
                    List.of(
                            new OpenAIRequest.Message(
                                    "system",
                                    "You convert citizen complaints into formal government email language. Be polite, clear, and professional."
                            ),
                            new OpenAIRequest.Message(
                                    "user",
                                    rawText
                            )
                    )
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<OpenAIRequest> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<OpenAIResponse> response =
                    restTemplate.postForEntity(
                            "http://api.openai.com/v1/chat/completions",
                            entity,
                            OpenAIResponse.class
                    );

            return response.getBody()
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();
        } catch (Exception e) {
            return rawText;
        }
    }
}
