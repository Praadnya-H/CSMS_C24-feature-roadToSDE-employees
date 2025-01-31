package com.cars24.csms.data.dao;

import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignUpRequest;

public interface AppUserDao {
    String getAppUserDetails(LoginRequest loginRequest);
    boolean checkIfUserExists(String username);
    String createUser(SignUpRequest signUpRequest);
    int getUserId(String username);
}
