package com.devsu.audittransactions.application;

import com.devsu.audittransactions.domain.interfaces.IReportService;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {


    @Override
    public byte[] generateReport(List<TransactionAudit> transactionAudits) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream("/report/Tranactions-report.jrxml");

        JasperReport jasperReport = null;


        JRBeanCollectionDataSource dataSource = null;

        try {
            jasperReport = JasperCompileManager.compileReport(reportStream);
            dataSource = new JRBeanCollectionDataSource(transactionAudits);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Leandro Amariles to devsu");

        JasperPrint jasperPrint = null;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


}
