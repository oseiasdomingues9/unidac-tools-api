package com.unidac.tools.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs{

    @Id
    @SequenceGenerator(name = "logsSequence",sequenceName = "logs_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logsSequence")
    private Long id;
    private Long integrationId;
    private String integrationName;
    private Long refId1;
    private String refName1;
    private Long refId2;
    private String refName2;
    private String message;
    private String url;
    @Lob@Basic
    private String msgRequest;
    @Lob@Basic
    private String msgResponse;
    private String status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private LocalDateTime time;
    private String favorite;
    private String visible;
    private String requestMethod;
    private String contentType;
    private String urlReinstate;
    private String reinstate;

}
