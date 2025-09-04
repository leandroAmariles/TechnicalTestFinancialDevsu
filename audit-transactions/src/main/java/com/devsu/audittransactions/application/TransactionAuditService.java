package com.devsu.audittransactions.application;

import com.devsu.audittransactions.domain.interfaces.IReportService;
import com.devsu.audittransactions.domain.interfaces.ITransactionsAuditService;

import com.devsu.audittransactions.infraestructure.adapters.controller.dto.in.ExtractRequest;
import com.devsu.audittransactions.infraestructure.adapters.database.ITransactionAuditAdapter;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionAuditService implements ITransactionsAuditService {

    private final ITransactionAuditAdapter iTransactionAuditAdapter;

    private final IReportService iReportService;

    @Override
    public byte[] generateExtract(String startDate, String endDate, String clientName) {
        ExtractRequest extractRequest = new ExtractRequest(clientName, startDate, endDate);
        List<TransactionAudit> transactionAudits = iTransactionAuditAdapter.getTransactionAudits(extractRequest);
        byte[] result = null;
        try {
            result = iReportService.generateReport(transactionAudits);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
