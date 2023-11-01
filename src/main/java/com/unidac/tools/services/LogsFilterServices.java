package com.unidac.tools.services;

import com.unidac.tools.dto.FilterDTO;
import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.dto.LogsPageDTO;
import com.unidac.tools.repositories.filter.LogsFilterRepository;
import com.unidac.tools.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogsFilterServices {

    private final LogsFilterRepository logsFilterRepository;

    public LogsPageDTO searchByFilter(FilterDTO filterDTO){
        List<LogsDTO> logsDTOList = new ArrayList<>();
        Integer total = 0;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            String sql = logsFilterRepository.buildSqlQuery(filterDTO,parameterSource,true,false);
            logsDTOList = logsFilterRepository.logsByFilter(sql,parameterSource);
            total = logsDTOList.size();
            logsDTOList = PaginationUtil.paginate(logsDTOList,filterDTO.getPage(),filterDTO.getRows());
            log.info("Runtime: " + stopWatch.getTime() + "ms");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new LogsPageDTO(logsDTOList, total);
    }

    private Integer getTotalElements(FilterDTO filterDTO) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String sql = logsFilterRepository.buildSqlQuery(filterDTO,parameterSource,false,false);
        return logsFilterRepository.getTotalRowCount(sql,parameterSource);
    }

}
