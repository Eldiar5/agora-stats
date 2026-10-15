package org.agora.statistic.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {

    // Формат ответа agora-api: {"token": {"accessToken": ..., "refreshToken": ..., "tokenType": ...}, "login": ...}
    private TokenResponseDto token;
    private String login;

}
