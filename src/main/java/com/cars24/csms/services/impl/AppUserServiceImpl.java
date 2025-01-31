package com.cars24.csms.services.impl;

import com.cars24.csms.data.dao.impl.AppUserDaoImpl;
import com.cars24.csms.data.req.SignUpRequest;
import com.cars24.csms.data.res.ApiResponse;
import com.cars24.csms.exceptions.UserServiceException;
import com.cars24.csms.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserDaoImpl appUserDetailsDao;

    @Override
    public ResponseEntity<ApiResponse> signup(SignUpRequest signUpRequest) {
        boolean res = appUserDetailsDao.checkIfUserExists(signUpRequest.getUsername());
        ApiResponse apiResponse = new ApiResponse();
        if (res) {
            throw new UserServiceException("User already exists!");
        } else {
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setSuccess(true);
            apiResponse.setMessage("User SignUp Successful!");
            apiResponse.setData(null);
            apiResponse.setService("APP_USER - " + HttpStatus.OK.value());
            appUserDetailsDao.createUser(signUpRequest);
        }
        log.info("[signupUser] apiRes IN SERVICE: {}", apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }
}
