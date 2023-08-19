package com.unidac.tools.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametersError{
    private String identifier;
    private String company;
    private String exist;
    private LocalDateTime date = LocalDateTime.now();
}
