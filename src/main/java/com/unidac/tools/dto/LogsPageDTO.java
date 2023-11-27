package com.unidac.tools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsPageDTO {

    private List<LogsDTO> logsDTO;
    private Long totalRows;
}
