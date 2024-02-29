package com.example.tk_etlproc.db.log.repo;

import com.example.tk_etlproc.db.log.entities.ArchiveLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArchiveLogRepository extends JpaRepository<ArchiveLog, UUID> {
}