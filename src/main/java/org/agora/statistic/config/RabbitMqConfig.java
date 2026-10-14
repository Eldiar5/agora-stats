package org.agora.statistic.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // --- СУЩЕСТВУЮЩИЕ ОЧЕРЕДИ (POSTS) ---
    @Value("${rabbitmq.queue.post-created:stats.post.created.queue}") // <-- Добавили дефолт
    private String postCreatedQueue;

    // --- НОВЫЕ ОЧЕРЕДИ (LIKES) ---
    // Если конфиг не пришел, используем значение после двоеточия
    @Value("${rabbitmq.queue.like-created:stats.like.created.queue}")
    private String likeCreatedQueue;

    @Value("${rabbitmq.queue.like-deleted:stats.like.deleted.queue}")
    private String likeDeletedQueue;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // --- БИНЫ ДЛЯ СОЗДАНИЯ ОЧЕРЕДЕЙ ---

    @Bean
    public Queue postCreatedQueue() {
        return new Queue(postCreatedQueue, true);
    }

    @Bean
    public Queue likeCreatedQueue() {
        return new Queue(likeCreatedQueue, true);
    }

    @Bean
    public Queue likeDeletedQueue() {
        return new Queue(likeDeletedQueue, true);
    }
}