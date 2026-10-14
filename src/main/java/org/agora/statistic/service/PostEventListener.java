package org.agora.statistic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.agora.statistic.dto.event.PostCreatedEvent;

@Service
public class PostEventListener {

    Logger logger = LoggerFactory.getLogger(PostEventListener.class);

    @RabbitListener(queues = "stats.post.created.queue")
    public void handlePostCreated(PostCreatedEvent event) {
        logger.info("📨 [EVENT] Мгновенное уведомление! Пользователь {} создал новый пост с (ID: {})",
                event.getAuthorUsername(), event.getPostId());
    }

}
