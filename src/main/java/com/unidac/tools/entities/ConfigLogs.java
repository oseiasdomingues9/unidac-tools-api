package com.unidac.tools.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigLogs{

    @Id
    @SequenceGenerator(name = "configLogsSequence",sequenceName = "config_log_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configLogsSequence")
    private Long id;
    private boolean integrationId;
    private boolean refId1;
    private boolean refName1;
    private boolean refId2;
    private boolean refName2;
    private boolean message;

    private boolean time;
    private boolean requestMethod;
    private boolean contentType;
    private boolean debugMode;

    private String username;
}
