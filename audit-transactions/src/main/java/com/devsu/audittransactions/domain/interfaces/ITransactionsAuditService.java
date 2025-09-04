package com.devsu.audittransactions.domain.interfaces;

import com.devsu.audittransactions.infraestructure.adapters.controller.dto.in.ExtractRequest;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;

import java.util.List;

public interface ITransactionsAuditService {

    byte[] generateExtract(String startDate, String endDate, String clientName);



}
