package com.devsu.audittransactions.domain.interfaces;

import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface IReportService {

    public byte[] generateReport(List<TransactionAudit> transactionAudits) throws JRException;
}
