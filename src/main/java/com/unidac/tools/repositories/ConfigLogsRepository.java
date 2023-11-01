package com.unidac.tools.repositories;

import com.unidac.tools.entities.ConfigLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigLogsRepository extends JpaRepository<ConfigLogs, String> {
    ConfigLogs findByUserId(String userId);
}
