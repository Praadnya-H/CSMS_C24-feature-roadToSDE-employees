package com.cars24.csms.data.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Valid
@Data
public class GetEmployeeReq {

    @Valid
    @Min(value = 1, message = "Employee ID cannot be NEGATIVE!")
    int employee_id;
}
