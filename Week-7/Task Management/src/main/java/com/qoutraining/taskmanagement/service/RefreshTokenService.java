package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.exception.UserNotFoundException;
import com.qoutraining.taskmanagement.model.entity.RefreshToken;
import com.qoutraining.taskmanagement.model.entity.User;
import com.qoutraining.taskmanagement.repository.RefreshTokenRepository;
import com.qoutraining.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public RefreshToken createRefreshToken(Long userId){
        User user=userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found"));

        refreshTokenRepository.deleteByUser(user);
        refreshTokenRepository.flush();
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        return refreshTokenRepository.save(token);
    }

    @Transactional
    public RefreshToken validateRefreshToken(String token){
        RefreshToken refreshToken= refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        if(refreshToken.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token was expired. Please make a new sign in request");
        }
        return refreshToken;
    }
}
