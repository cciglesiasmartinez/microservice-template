package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.messaging.kafka;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.MessageBroker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducer implements MessageBroker {

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void sendMessage(DomainEvent event) {
        String topicName = "item.events";
        CompletableFuture<SendResult<String, DomainEvent>> future = kafkaTemplate.send(topicName, event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                MDC.put("requestId", event.getRequestId());
                log.info("Sent event {} with offset {}.", event.getEventType(), result.getRecordMetadata().offset());
                MDC.clear();
            } else {
                MDC.put("requestId", event.getRequestId());
                log.info("Unable to send event {} due to {}.", event.getEventType(), ex.getMessage());
                MDC.clear();
                throw new RuntimeException();
            }
        });
    }
}