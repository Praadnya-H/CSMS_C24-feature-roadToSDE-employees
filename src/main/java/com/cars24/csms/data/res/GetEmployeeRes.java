package com.cars24.csms.data.res;

import com.cars24.csms.data.enums.EmployeeRole;
import lombok.Data;

@Data
public class GetEmployeeRes {

    private String name;
    private String phone;
    private String email;
    private EmployeeRole role;
    private double salary;

}
