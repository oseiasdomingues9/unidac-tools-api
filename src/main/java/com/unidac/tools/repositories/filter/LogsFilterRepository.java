package com.unidac.tools.repositories.filter;

import com.unidac.tools.dto.FilterDTO;
import com.unidac.tools.dto.LogsDTO;
import com.unidac.tools.repositories.filter.mapper.LogRowMapper;
import com.unidac.tools.utils.QueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class LogsFilterRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<LogsDTO> logsByFilter(String sql, MapSqlParameterSource parameterSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        log.info("SELECT * FROM logs WHERE 1=1 {}",sql);
        return namedParameterJdbcTemplate.query("SELECT * FROM logs WHERE 1=1" + sql, parameterSource, new LogRowMapper());
    }

    public String buildSqlQuery(FilterDTO filterDTO,MapSqlParameterSource params,boolean sort,boolean pagination){
        StringBuilder sql = new StringBuilder();

        if (filterDTO.getDateStart() != null && filterDTO.getDateEnd() != null) {
            sql.append(" AND date BETWEEN :start AND :end");
            params.addValue("start", filterDTO.getDateStart());
            params.addValue("end", filterDTO.getDateEnd() );
        }

        if (pagination) {
            sql.append(" LIMIT :limit OFFSET :offset");
            params.addValue("limit", filterDTO.getRows());
            params.addValue("offset", filterDTO.getPage() * filterDTO.getRows());
        }

        if(sort && (filterDTO.getSortField() != null)) {
            sql.append(" ORDER BY ").append(QueryUtils.toSnakeCase(filterDTO.getSortField()));
            if (filterDTO.getSortOrder() == 1) {
                sql.append(" ASC");
            } else {
                sql.append(" DESC");
            }
        }
        return sql.toString();
    }

    public Integer getTotalRowCount(String sql, MapSqlParameterSource parameterSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject("SELECT COUNT(*) FROM logs WHERE 1=1" + sql, parameterSource,Integer.class);
    }
}
