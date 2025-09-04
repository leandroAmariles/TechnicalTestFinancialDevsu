package com.devsu.audittransactions.infraestructure.adapters.database.adapter;

import com.devsu.audittransactions.infraestructure.adapters.controller.dto.in.ExtractRequest;
import com.devsu.audittransactions.infraestructure.adapters.database.ITransactionAuditAdapter;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import com.devsu.audittransactions.infraestructure.adapters.database.repository.TransactionAuditRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionAuditAdapter implements ITransactionAuditAdapter {

    private final TransactionAuditRepository transactionAuditRepository;


    @Override
    public void saveTransactionAudit(TransactionAudit transactionAudit) {
        transactionAuditRepository.save(transactionAudit);
    }

    @Override
    public List<TransactionAudit> getTransactionAudits(ExtractRequest extractRequest) {

            TransactionAudit transactionAudit = TransactionAudit.builder()
                    .clientName(extractRequest.clientName())
                    .build();

            ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                    .withIgnoreCase("clientName")
                    .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

            Example<TransactionAudit> example = Example.of(transactionAudit, exampleMatcher);

        return transactionAuditRepository.findAll(specification(transactionAudit,
                extractRequest.startDate(), extractRequest.endDate(), example));
        }


        private Specification<TransactionAudit> specification(TransactionAudit transactionAudit, String startDate, String endDate,
                                                              Example<TransactionAudit> example) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return (Specification<TransactionAudit>) (root, query, builder) -> {
                final List<Predicate> predicates = new ArrayList<>();

                String clientName = transactionAudit.getClientName()!=null?transactionAudit.getClientName():null;

                if (clientName != null && !clientName.isBlank()) {
                    predicates.add(builder.equal(builder.lower(root.get("clientName")), clientName.toLowerCase()));
                }
                try {
                    LocalDateTime start = LocalDateTime.parse(startDate, formatter);
                    LocalDateTime end = LocalDateTime.parse(endDate, formatter);

                    predicates.add(builder.between(root.get("createDate"), start, end));
                } catch (DateTimeParseException e) {
                    log.error("Formato de fecha inválido: {}", e.getParsedString());
                    throw new RuntimeException("Formato de fecha inválido", e);
                }
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        }
}
