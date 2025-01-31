package com.cars24.csms.data.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class LoginRequest {

    @Valid
    @NotBlank(message = "Username cannot be EMPTY!")
    private String username;

    @Valid
    @NotBlank(message = "Password cannot be EMPTY!")
    private String password;
}
