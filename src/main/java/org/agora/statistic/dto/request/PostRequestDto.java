package org.agora.statistic.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostRequestDto {

    private String topic;
    private String text;
    private List<String> tags;

}
