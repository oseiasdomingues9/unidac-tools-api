package com.unidac.tools.mapper;

import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.entities.Logs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface LogsMapper {

    @Mapping(target = "msgRequest",ignore = true)
    @Mapping(target = "msgResponse",ignore = true)
    Logs toLogs(LogsDTO logsDTO);

    LogsDTO toDTO(Logs logs);
}
