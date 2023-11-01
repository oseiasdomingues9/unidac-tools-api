package com.unidac.tools.utils;

import com.unidac.tools.dto.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@UtilityClass
public class MessageUtils {

    public ResponseDTO successMessage(String message, Object data){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return new ResponseDTO(
                message,
                HttpStatus.OK.value(),
                data,
                Instant.now().toString(),
                uri.getPath()
        );
    }
}