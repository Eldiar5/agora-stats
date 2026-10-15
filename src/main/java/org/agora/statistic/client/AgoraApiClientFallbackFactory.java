package org.agora.statistic.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

// Логирует настоящую причину сбоя (таймаут, 4xx/5xx, ошибка десериализации) и отдаёт обычный fallback
@Component
@RequiredArgsConstructor
public class AgoraApiClientFallbackFactory implements FallbackFactory<AgoraApiClient> {

    private static final Logger logger = LoggerFactory.getLogger(AgoraApiClientFallbackFactory.class);

    private final AgoraApiClientFallback fallback;

    @Override
    public AgoraApiClient create(Throwable cause) {
        logger.warn("[Fallback] Причина: {}: {}", cause.getClass().getSimpleName(), cause.getMessage());
        return fallback;
    }
}
