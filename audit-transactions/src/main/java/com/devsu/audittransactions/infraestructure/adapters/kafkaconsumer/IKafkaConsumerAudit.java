package com.devsu.audittransactions.infraestructure.adapters.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IKafkaConsumerAudit {

    void auditTransactionListener(ConsumerRecord<String, String> message);
}
