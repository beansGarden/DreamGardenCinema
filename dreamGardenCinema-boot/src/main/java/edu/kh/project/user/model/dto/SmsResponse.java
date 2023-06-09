package edu.kh.project.user.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class SmsResponse {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
}
