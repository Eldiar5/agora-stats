package org.agora.statistic.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCreatedEvent {

    private Long postId;
    private String whoLiked;
    private Long postAuthorId; // Чей пост лайкнули (полезно для уведомлений автору)
    private LocalDateTime createdAt;

}
