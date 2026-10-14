package org.agora.statistic.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.agora.statistic.dto.request.LoginRequestDto;
import org.agora.statistic.dto.request.RegistrationRequestDto;
import org.agora.statistic.dto.response.InfoResponseDto;
import org.agora.statistic.dto.response.LoginResponseDto;
import org.agora.statistic.dto.response.PostResponseDto;
import org.agora.statistic.dto.response.RegistrationResponseDto;

import java.util.List;

@FeignClient(name = "AGORA-API", fallback = AgoraApiClientFallback.class)
@Headers("Content-Type: application/json")
public interface AgoraApiClient {

    // --- RegistrationController ---
    @PostMapping("/api/v1/register")
    RegistrationResponseDto register(@RequestBody RegistrationRequestDto registrationRequestDto);

    // --- AuthenticationController ---
    @PostMapping("/api/v1/auth/login")
    LoginResponseDto login(@RequestBody LoginRequestDto loginRequest);

    // --- InfoController ---
    @GetMapping("/api/v1/info/all")
    List<InfoResponseDto> getAllUsers(@RequestHeader("Authorization") String token);

    // --- PostController ---
    @GetMapping("/api/v1/post/all")
    List<PostResponseDto> getAllPosts(@RequestHeader("Authorization") String token);

}
