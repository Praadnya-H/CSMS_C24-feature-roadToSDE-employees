package com.cars24.csms.data.req;

import com.cars24.csms.data.enums.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class SignUpRequest {
    @Valid
    @Size(min = 10, max = 40,message = "Email must be 10 to 40 characters only!!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is INVALID!!")
    @NotBlank(message = "Username cannot be EMPTY!")
    private String username;

    @Valid
    @NotBlank(message = "Password cannot be EMPTY!")
    @Size(min = 8, max = 20, message = "Password must be 8 to 20 characters only!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password should contain uppercase,lowercase,digit,special character!!")
    private String password;

    private UserType user_type;
}
