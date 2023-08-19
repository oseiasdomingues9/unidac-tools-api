package com.unidac.tools.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {

    private String exception;
    private String method;
    private Long line;
    private String message;
    private String messageCustom;
    private String classPath;
    private LocalDateTime date = LocalDateTime.now();

}
