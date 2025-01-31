package com.cars24.csms.data.req;

import com.cars24.csms.data.enums.EmployeeRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Valid
public class CreateEmployeeReq {
    @Valid
    @Size(min = 3, max = 45, message = "Name should be between 3 to 45 characters only!")
    @NotNull
    private String name;

    @Valid
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number should be 10 digits only!")
    @NotNull
    private String phone;

    @Valid
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is INVALID!!")
    @Size(min = 6, max = 45, message = "Email should be between 6 to 45 characters only!")
    private String email;

    private EmployeeRole role;

    @Valid
    @NotNull
    @Min(value = 1000, message = "Salary is too low!")
    private Double salary;
}
