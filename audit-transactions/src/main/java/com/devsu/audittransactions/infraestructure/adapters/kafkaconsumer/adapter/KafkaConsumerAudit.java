package com.devsu.audittransactions.infraestructure.adapters.kafkaconsumer.adapter;

import com.devsu.audittransactions.domain.interfaces.ITransactionsAuditService;
import com.devsu.audittransactions.infraestructure.adapters.database.ITransactionAuditAdapter;
import com.devsu.audittransactions.infraestructure.adapters.database.entity.TransactionAudit;
import com.devsu.audittransactions.infraestructure.adapters.kafkaconsumer.IKafkaConsumerAudit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerAudit implements IKafkaConsumerAudit {

    private final ITransactionAuditAdapter transactionAuditAdapter;

    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = "transaction-audit-updater", groupId = "query-data-management-group")
    public void auditTransactionListener(ConsumerRecord<String, String> record) {
        log.info("Received record: {} {}", record.key(), record.value());
        TransactionAudit transactionAudit = null;
        try {
            transactionAudit = objectMapper.readValue(record.value(), TransactionAudit.class);
        }catch (Exception e){
            log.error("Error while reading transaction record: {} {} {}", record.key(), record.value(), e.getMessage());
        }
        transactionAuditAdapter.saveTransactionAudit(transactionAudit);
    }
}
