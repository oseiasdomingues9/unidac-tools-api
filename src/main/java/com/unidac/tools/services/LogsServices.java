package com.unidac.tools.services;


import com.unidac.tools.dto.FilterDTO;
import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.dto.LogsPageDTO;
import com.unidac.tools.entities.Logs;
import com.unidac.tools.repositories.LogsRepository;
import com.unidac.tools.utils.DateUtils;
import com.unidac.tools.utils.JsonUtils;
import com.unidac.tools.utils.XMLUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LogsServices extends AbstractServices{

    private final LogsRepository logsRepository;

    public void saveLog(LogsDTO logsDTO){
        logger.info("Salvando log");
        Logs logs = logsMapper.toLogs(logsDTO);
        defaultFields(logsDTO, logs);
        configByType(logsDTO, logs);
        logsRepository.save(logs);
    }

    private void configByType(LogsDTO logsDTO, Logs logs) {
        if(logsDTO.getContentType().equals("JSON")) {
            logs.setMsgRequest(JsonUtils.checkIfTransformJson(logsDTO.getMsgRequest()));
            logs.setMsgResponse(JsonUtils.checkIfTransformJson(logsDTO.getMsgResponse()));
        } else if (logsDTO.getContentType().equals("XML")) {
            logs.setMsgRequest(XMLUtils.prettyFormat(logsDTO.getMsgRequest()));
            logs.setMsgResponse(XMLUtils.prettyFormat(logsDTO.getMsgRequest()));
        }
    }

    private static void defaultFields(LogsDTO logsDTO, Logs logs) {
        logs.setFavorite(StringUtils.isBlank(logsDTO.getFavorite()) ? "N" : logsDTO.getFavorite());
        logs.setVisible(StringUtils.isBlank(logsDTO.getVisible()) ? "N" : logsDTO.getVisible());
        logs.setContentType(StringUtils.isBlank(logsDTO.getContentType()) ? "JSON" : logsDTO.getContentType());
        logsDTO.setContentType(StringUtils.isBlank(logsDTO.getContentType()) ? "JSON" : logsDTO.getContentType());
        logs.setReinstate("N");
        logs.setDate(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        logs.setTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    public LogsPageDTO searchByFilter(FilterDTO filterDTO){
        try {
            Map<String,Object> queryList = new LinkedHashMap<>();
            queryList.put("start", DateUtils.toLocalDateTime(filterDTO.getDateStart()));
            queryList.put("end",DateUtils.toLocalDateTime(filterDTO.getDateEnd()));

            Sort sort = Sort.by(filterDTO.getSortOrder() == 1 ? Sort.Direction.ASC : Sort.Direction.DESC,filterDTO.getSortField());
            PageRequest pageRequest = PageRequest.of(filterDTO.getPage(),filterDTO.getRows(),sort);
            Page<Logs> logsList = logsRepository.findBetween(filterDTO.getDateStart(),filterDTO.getDateEnd(),pageRequest);

            List<LogsDTO> logsDTOList = new ArrayList<>();
            logsList.forEach(x -> logsDTOList.add(logsMapper.toDTO(x)));
            return new LogsPageDTO(logsDTOList,logsDTOList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
