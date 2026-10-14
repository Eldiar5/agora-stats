package org.agora.statistic.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDeletedEvent {

    private Long postId;
    private String whoUnliked; // Кто убрал лайк
    private LocalDateTime deletedAt;

}
