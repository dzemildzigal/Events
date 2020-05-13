package com.lambda.NotificationService.Configuration;
import com.lambda.NotificationService.Service.RabbitMQConsumer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
@Configuration
    public class RabbitMQConfiguration {

        @Value("${rmq.queue}")
        String queueName;

        @Value("${rmq.exchange}")
        String exchange;

        @Value("${spring.rabbitmq.username}")
        String username;

        @Value("${spring.rabbitmq.password}")
        private String password;

        @Value("${spring.rabbitmq.host}")
        private String host;


        @Value("${rmq.routingKey}")
            private String routingKey;

        @Bean
        Queue queue() {
            return new Queue(queueName, false);
        }

        @Bean
        DirectExchange exchange() {
            return new DirectExchange(exchange);
        }
        @Bean
        MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQConsumer());
        return simpleMessageListenerContainer;

          }
        @Bean
        ConnectionFactory connectionFactory() {
            CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
            cachingConnectionFactory.setUsername(username);
            cachingConnectionFactory.setUsername(password);
            return cachingConnectionFactory;
        }

        @Bean
        MessageListenerContainer messageListenerContainer() {
            SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
            simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
            simpleMessageListenerContainer.setQueues(queue());
            simpleMessageListenerContainer.setMessageListener(new RabbitMQConsumer());
            return simpleMessageListenerContainer;

        }

        @Bean
        Binding binding(Queue queue, DirectExchange exchange) {
            return BindingBuilder.bind(queue).to(exchange).with(routingKey);
        }

        @Bean
        public AmqpTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
            return new RabbitTemplate(connectionFactory);
        }
    }

