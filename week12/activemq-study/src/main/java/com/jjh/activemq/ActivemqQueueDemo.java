package com.jjh.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: activemq-study
 * @description
 * @author: jjh
 * @create: 2022-03-17 15:13
 **/
public class ActivemqQueueDemo {


    public static void main(String[] args) {

        //定义目的单元
        Destination destination = new ActiveMQQueue("jjh.queue");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //消费者
            MessageConsumer consumer = session.createConsumer(destination);
            final AtomicInteger count = new AtomicInteger(0);
            //开启监听
            consumer.setMessageListener(new MessageListener(){
                @Override
                public void onMessage(Message message) {
                    System.out.println(count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
                }
            });

            //生产者
            MessageProducer producer = session.createProducer(destination);
            for (int i = 1; i <= 100; i++){
                TextMessage message = session.createTextMessage(i + "message");
                producer.send(message);
            }

            Thread.sleep(200000);
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
