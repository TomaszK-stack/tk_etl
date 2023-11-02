package com.example.tk_etlproc.db.log.repo;

import com.example.tk_etlproc.db.log.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}