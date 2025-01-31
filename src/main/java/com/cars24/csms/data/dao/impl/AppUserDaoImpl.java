package com.cars24.csms.data.dao.impl;

import com.cars24.csms.data.dao.AppUserDao;
import com.cars24.csms.data.entities.AppUserDetailsEntity;
import com.cars24.csms.data.repositories.AppUserRepository;
import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserDaoImpl implements AppUserDao {
    private final AppUserRepository appUserRepository;

    @Override
    public String getAppUserDetails(LoginRequest loginRequest) {
        AppUserDetailsEntity appUserDetailsEntity = appUserRepository.findAppUserDetailsByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        log.info("[getAppUserDetails] appUserDetails IN DAO: {}", appUserDetailsEntity);
        return "";
    }

    @Override
    public boolean checkIfUserExists(String username) {
        log.info("[checkIfUserExists] IN DAO");
        return appUserRepository.existsByUsername(username);
    }

    @Override
    public String createUser(SignUpRequest signUpRequest) {
        log.info("[createUser] IN DAO");
        ObjectMapper objectMapper = new ObjectMapper();
        AppUserDetailsEntity appUserDetailsEntity = objectMapper.convertValue(signUpRequest, AppUserDetailsEntity.class);
        appUserDetailsEntity.setIs_active(true);
        appUserRepository.save(appUserDetailsEntity);
        return "";
    }

    @Override
    public int getUserId(String username) {
        AppUserDetailsEntity appUserDetailsEntity = appUserRepository.findByUsername(username);
        int userId = appUserDetailsEntity.getId();
        log.info("[getUserId] IN DAO: {}",userId);
        return userId;
    }
}
