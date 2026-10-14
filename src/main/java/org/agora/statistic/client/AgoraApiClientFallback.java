package org.agora.statistic.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.agora.statistic.dto.request.LoginRequestDto;
import org.agora.statistic.dto.request.RegistrationRequestDto;
import org.agora.statistic.dto.response.InfoResponseDto;
import org.agora.statistic.dto.response.LoginResponseDto;
import org.agora.statistic.dto.response.PostResponseDto;
import org.agora.statistic.dto.response.RegistrationResponseDto;

import java.util.Collections;
import java.util.List;

@Component
public class AgoraApiClientFallback implements AgoraApiClient {

    public static Logger logger = LoggerFactory.getLogger(AgoraApiClientFallback.class);

    @Override
    public RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto) {
        logger.error("[Fallback] Не удалось выполнить регистрацию. API недоступен.");
        return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        logger.error("[Fallback] Не удалось залогиниться. API недоступен.");
        return null;
    }

    @Override
    public List<InfoResponseDto> getAllUsers(String token) {
        logger.error("[Fallback] Не удалось получить пользователей. Возвращаем пустой список.");
        return Collections.emptyList();
    }

    @Override
    public List<PostResponseDto> getAllPosts(String token) {
        logger.error("[Fallback] Не удалось получить посты. Возвращаем пустой список.");
        return Collections.emptyList();
    }
}
