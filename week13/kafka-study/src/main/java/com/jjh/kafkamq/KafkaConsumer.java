package com.jjh.kafkamq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @program: kafka-study
 * @description
 * @author: jjh
 * @create: 2022-03-24 19:35
 **/
@Component
public class KafkaConsumer {

    /**
     *
     * @param record
     * @param ack
     */
    @KafkaListener(topics = "jjhTest", groupId = "jjhTestGroup")
    public void listenJJHGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        //手动提交offset
        ack.acknowledge();
    }


}
