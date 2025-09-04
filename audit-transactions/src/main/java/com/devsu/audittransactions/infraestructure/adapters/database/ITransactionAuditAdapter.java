package com.devsu.audittransactions.infraestructure.adapters.database;

import com.devsu.audittransactions.infraestructure.adapters.controller.dto.in.ExtractRequest;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;

import java.util.List;

public interface ITransactionAuditAdapter {

    void saveTransactionAudit(TransactionAudit transactionAudit);

    List<TransactionAudit> getTransactionAudits(ExtractRequest extractRequest);
}
