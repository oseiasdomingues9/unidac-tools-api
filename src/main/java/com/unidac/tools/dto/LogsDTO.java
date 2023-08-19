package com.unidac.tools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsDTO{
    private Long integrationId;
    private String integrationName;
    private Long refId1;
    private String refName1;
    private Long refId2;
    private String refName2;
    private String message;
    private String url;
    private Object msgRequest;
    private Object msgResponse;
    private String status;
    private LocalDate date;
    private LocalDateTime time;
    private String favorite;
    private String visible;
    private String requestMethod;
    private String contentType;
    private String urlReinstate;
    private String reinstate;
}
