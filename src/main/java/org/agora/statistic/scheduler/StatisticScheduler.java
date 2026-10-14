package org.agora.statistic.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.agora.statistic.client.AgoraApiClient;
import org.agora.statistic.dto.request.LoginRequestDto;
import org.agora.statistic.dto.request.RegistrationRequestDto;
import org.agora.statistic.dto.response.InfoResponseDto;
import org.agora.statistic.dto.response.LoginResponseDto;
import org.agora.statistic.dto.response.PostResponseDto;
import org.agora.statistic.dto.response.RegistrationResponseDto;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticScheduler {

    private final String BOT_LOGIN = "stat-bot";
    private final String BOT_PASSWORD = "statistic";

    private final AgoraApiClient agoraApiClient;

    @Scheduled(fixedDelay = 30000) // Раз в 30 секунд
    public void gatherStatistics() {
        log.info("--- [АНАЛИТИКА] Сбор статистики начат ---");

        // 1. Получаем токен (с попыткой регистрации)
        String token = authenticateBot();

        if (token == null) {
            log.warn("[Analytics-Service] Не удалось получить доступ к API. Пропускаем итерацию.");
            return;
        }

        try {
            // --- Сбор метрик пользователей ---
            List<InfoResponseDto> users = agoraApiClient.getAllUsers(token); // <-- ВЫЗОВ API

            // --- Сбор метрик постов ---
            List<PostResponseDto> posts = agoraApiClient.getAllPosts(token); // <-- ВЫЗОВ API

            // --- Логирование ---
            if (users.isEmpty() && posts.isEmpty()) {
                log.warn("[Analytics-Service] Получены пустые данные (возможно API недоступен/Fallback).");
            } else {
                log.info("--- [АНАЛИТИКА] {} ---", LocalTime.now());
                log.info("    Всего пользователей: {}", users.size());
                log.info("    Всего постов: {}", posts.size());
                log.info("---------------------------------");
            }

        } catch (Exception e) {
            log.error("[Analytics-Service] Ошибка при сборе данных: ", e);
        }
    }

    private boolean registerBot() {
        try {
            RegistrationRequestDto request = new RegistrationRequestDto();
            request.setUserType(0);
            request.setLogin("stat-bot");
            request.setPassword("statistic");
            request.setName("info");
            request.setLastName("bot");
            request.setBirthDate("2025-01-01");

            RegistrationResponseDto response = agoraApiClient.register(request);

            // САМОЕ ВАЖНОЕ: Проверка на null от Fallback
            if (Objects.isNull(response)) {
                log.warn("[Analytics-Service] Не удалось зарегистрировать бота.");
                return false;
            }

            log.info("[Analytics-Service] Бот УСПЕШНО зарегистрирован!");

        } catch (Exception e) {
            log.error("[Analytics-Service] Ошибка при попытке регистрации: {}", e.getMessage());
        }
        return true;
    }

    private String authenticateBot() {
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setLogin(BOT_LOGIN);
        loginRequest.setPassword(BOT_PASSWORD);

        log.info("[Analytics-Service] Попытка входа в систему как {}...", BOT_LOGIN);

        // Попытка 1: Вход
        LoginResponseDto loginResponse = agoraApiClient.login(loginRequest);

        if (isValidToken(loginResponse)) {
            return "Bearer " + loginResponse.getToken();
        }

        // Если пришел null (Fallback сработал), значит, скорее всего, пользователя нет (или API лежит).
        // Попробуем зарегистрироваться.
        log.info("[Analytics-Service] Вход не удался. Пробуем зарегистрировать бота...");

        boolean registrationSuccess = registerBot();

        if (registrationSuccess) {
            // Попытка 2: Вход после регистрации
            log.info("[Analytics-Service] Регистрация прошла успешно. Пробуем войти снова...");
            loginResponse = agoraApiClient.login(loginRequest);

            if (isValidToken(loginResponse)) {
                log.info("[Analytics-Service] Бот зарегистрирован и залогинен!");
                return "Bearer " + loginResponse.getToken();
            }
        }

        log.error("[Analytics-Service] ФАТАЛЬНО: Не удалось ни войти, ни зарегистрироваться.");
        return null;
    }

    private boolean isValidToken(LoginResponseDto response) {
        return response != null && response.getToken() != null && !response.getToken().isEmpty();
    }
}
