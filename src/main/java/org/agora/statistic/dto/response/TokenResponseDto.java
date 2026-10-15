package org.agora.statistic.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType;

}
