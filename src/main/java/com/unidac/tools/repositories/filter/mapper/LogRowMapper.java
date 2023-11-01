package com.unidac.tools.repositories.filter.mapper;

import com.unidac.tools.dto.LogsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper implements RowMapper<LogsDTO> {
    @Override
    public LogsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setIntegrationId(rs.getLong("integration_id"));
        logsDTO.setIntegrationName(rs.getString("integration_name"));
        logsDTO.setRefId1(rs.getLong("ref_id1"));
        logsDTO.setDate(rs.getDate("date").toLocalDate());
        return logsDTO;
    }
}
