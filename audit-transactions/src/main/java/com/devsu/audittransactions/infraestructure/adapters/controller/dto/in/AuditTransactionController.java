package com.devsu.audittransactions.infraestructure.adapters.controller.dto.in;


import com.devsu.audittransactions.domain.interfaces.ITransactionsAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuditTransactionController {

    private final ITransactionsAuditService auditTransactionService;

    @GetMapping("/reportes")
    ResponseEntity<byte[]> generateExtractReport(
            @RequestParam("fechaInicio") String startDate,
            @RequestParam("fechaFin") String endDate,
            @RequestParam("nombreCliente") String clientName
    ) {
        byte[] report = auditTransactionService.generateExtract(startDate, endDate, clientName);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=extract_transactions.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(report);
    }
}