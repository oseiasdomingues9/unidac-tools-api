package com.unidac.tools.services;

import com.querydsl.core.BooleanBuilder;
import com.unidac.tools.dto.FilterDTO;
import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.dto.LogsPageDTO;
import com.unidac.tools.entities.Logs;
import com.unidac.tools.entities.QLogs;
import com.unidac.tools.repositories.LogsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogsFilterServices extends AbstractServices{

    private final LogsRepository logsRepository;

    @Transactional
    public LogsPageDTO searchByFilter(FilterDTO filterDTO){
        log.info(filterDTO.toString());
        List<LogsDTO> logsDTOList = new ArrayList<>();
        Integer total = 0;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            QLogs qLogs = QLogs.logs;
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            Sort sort = Sort.by(filterDTO.getSortOrder() == 1 ? Sort.Direction.ASC : Sort.Direction.DESC,filterDTO.getSortField());
            PageRequest pageRequest = PageRequest.of(filterDTO.getPage(),filterDTO.getRows(),sort);

            booleanBuilder.and(qLogs.date.between(toLocalDate(filterDTO.getDateStart()), toLocalDate(filterDTO.getDateEnd())));

            Page<Logs> page = logsRepository.findAll(booleanBuilder,pageRequest);

            page.forEach(x -> logsDTOList.add(logsMapper.toDTO(x)));

            stopWatch.stop();
            log.info("runtime {} ms", stopWatch.getTime());
            return new LogsPageDTO(logsDTOList,page.getTotalElements());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
