package com.jjh.kafkamq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjh.entity.Order;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: kafka-study
 * @description
 * @author: jjh
 * @create: 2022-03-24 19:33
 **/
@RestController
public class KafkaProducer {

    /**
     * topic的名称
     */
    private final static String TOPIC_NAME = "jjhTest";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        Order order = new Order(1000L, System.currentTimeMillis(),"USD2CNY", 6.5d);
        //发送
        kafkaTemplate.send(TOPIC_NAME,  order.getId().toString(), JSON.toJSONString(order));
    }
}
