package org.talangsoft.bookinventory.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;

@Configuration
public class ActiveMqConfiguration {

    public static final String ADDRESS = "vm://localhost";

    private BrokerService broker;

    @Bean(name="bookMgrQueueDestination")
    public Destination bookMgrQueueDestination(@Value("${jms.bookmgrqueue.name}") String bookMgrQueueName)
            throws JMSException {
        return new ActiveMQQueue(bookMgrQueueName);
    }

    @PostConstruct
    public void startActiveMQ() throws Exception {
        broker = new BrokerService();
        // configure the broker
        broker.setBrokerName("activemq-broker");
        broker.setDataDirectory("target");
        broker.addConnector(ADDRESS);
        broker.setUseJmx(false);
        broker.setUseShutdownHook(false);
        broker.start();
    }

    @PreDestroy
    public void stopActiveMQ() throws Exception {
        broker.stop();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(ADDRESS + "?broker.persistent=false");
    }
}
