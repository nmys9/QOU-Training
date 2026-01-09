package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.model.entity.RefreshToken;
import com.qoutraining.employeedirectory.model.entity.User;
import com.qoutraining.employeedirectory.repository.RefreshTokenRepository;
import com.qoutraining.employeedirectory.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
        refreshTokenRepository.flush();

        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken refreshToken=refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Invalid refresh token"));
        if(refreshToken.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepository.delete(refreshToken);
            throw new CredentialsExpiredException("Refresh token was expired. Please make a new sign in request");
        }
        return refreshToken;
    }

    @Override
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
