package com.unidac.tools.repositories;

import com.unidac.tools.entities.Logs;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;

public interface LogsRepository extends JpaRepository<Logs,Long>, QuerydslPredicateExecutor<Logs> {

    @Query(value = "Select * FROM logs where date(date) BETWEEN :start AND :end",nativeQuery = true)
    Page<Logs> findBetween(String start, String end, Pageable pageable);

    @Transactional
    Page<Logs> findByDateBetween(LocalDate dateStart, LocalDate dateEnd, PageRequest pageRequest);
}
