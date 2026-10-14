package org.agora.statistic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.agora.statistic.dto.event.LikeCreatedEvent;
import org.agora.statistic.dto.event.LikeDeletedEvent;

@Service
@Slf4j
public class LikeEventListener {

    @RabbitListener(queues = "${rabbitmq.queue.like-created}") // Вынесем имя очереди в настройки
    public void handleLikeCreated(LikeCreatedEvent event) {
        log.info("❤️ [EVENT] Получен лайк! Пользователь {} лайкнул пост (ID: {}). Время: {}",
                event.getWhoLiked(),
                event.getPostId(),
                event.getCreatedAt());
    }

    @RabbitListener(queues = "${rabbitmq.queue.like-deleted}")
    public void handleLikeDeleted(LikeDeletedEvent event) {
        log.info("💔 [EVENT] Лайк удален! Пользователь {} разлюбил пост (ID: {})",
                event.getWhoUnliked(),
                event.getPostId());
    }

}
