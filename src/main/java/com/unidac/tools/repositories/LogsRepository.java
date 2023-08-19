package com.unidac.tools.repositories;

import com.unidac.tools.entities.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogsRepository extends JpaRepository<Logs,Long> {

    @Query(value = "Select * FROM logs where date(date) BETWEEN :start AND :end",nativeQuery = true)
    Page<Logs> findBetween(String start, String end, Pageable pageable);
}
