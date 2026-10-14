package org.agora.statistic.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private String authorLogin;
    private Integer postId;

    private String topic;
    private String text;
    private List<String> tags;

    private LocalDateTime createdAt;

}
