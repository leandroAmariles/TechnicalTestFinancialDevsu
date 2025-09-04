package com.devsu.audittransactions.infraestructure.adapters.database.repository;

import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TransactionAuditRepository extends JpaRepository<TransactionAudit, Long>, JpaSpecificationExecutor<TransactionAudit> {

}
