package com.cars24.csms.controllers;

import com.cars24.csms.data.dao.impl.AppUserDaoImpl;
import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignUpRequest;
import com.cars24.csms.data.res.ApiResponse;
import com.cars24.csms.services.impl.AppUserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserDaoImpl appUserDetailsDao;
    private final AppUserServiceImpl appUserDetailsService;
//    private final PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public void getAppUserDetails(@Valid @RequestBody LoginRequest loginRequest){
        appUserDetailsDao.getAppUserDetails(loginRequest);
        log.info("[getAppUserDetails] loginReq IN CONTROLLER: {}", loginRequest);
    }

    @PostMapping("/signupUser")
    public ResponseEntity<ApiResponse> signupUser(@Valid @RequestBody SignUpRequest signUpRequest){
        log.info("[signupUser] signupReq IN CONTROLLER: {}", signUpRequest);
        return appUserDetailsService.signup(signUpRequest);
    }
}
