package com.cars24.csms.services;

import com.cars24.csms.data.req.SignUpRequest;
import com.cars24.csms.data.res.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    ResponseEntity<ApiResponse> signup(SignUpRequest signUpRequest);
}
