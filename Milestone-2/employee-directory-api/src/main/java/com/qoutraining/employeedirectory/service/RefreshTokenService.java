package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.entity.RefreshToken;
import com.qoutraining.employeedirectory.model.entity.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    RefreshToken validateRefreshToken(String token);
    void deleteByUser(User user);

}
