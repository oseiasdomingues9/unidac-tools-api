package com.unidac.tools.controllers;


import com.unidac.tools.dto.FilterDTO;
import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.dto.LogsPageDTO;
import com.unidac.tools.services.LogsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/logs")
@RestController
@RequiredArgsConstructor
@Tag(name = "Etiquetas")
public class LogsController {

    private final LogsServices logsServices;

    @PostMapping("/all")
    @Operation(summary = "Cadastro de etiquetas",description = "Recebe as etiqueta e cadastra no MV")
    @ApiResponse(responseCode = "200",description = "Retornar as etiqueta criadas ou com erros no cadastro",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LogsDTO.class)))
    )
    public ResponseEntity<LogsPageDTO> searchByFilter(FilterDTO filterDTO) {
        return new ResponseEntity<>(logsServices.searchByFilter(filterDTO),HttpStatus.OK);
    }


    @PostMapping("/create")
    public void create(LogsDTO logsDTO) {
        logsServices.saveLog(logsDTO);
    }


}
