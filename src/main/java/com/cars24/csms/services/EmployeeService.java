package com.cars24.csms.services;

import com.cars24.csms.data.req.CreateEmployeeReq;
import com.cars24.csms.data.req.UpdateEmployeeReq;
import com.cars24.csms.data.res.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    ResponseEntity<ApiResponse> createEmployee(CreateEmployeeReq createEmployeeReq);
    ResponseEntity<ApiResponse> getEmployee(int employee_id);
    ResponseEntity<ApiResponse> deleteEmployee(int employee_id);
    ResponseEntity<ApiResponse> updateEmployee(int employee_id, UpdateEmployeeReq updateEmployeeReq);
    ResponseEntity<ApiResponse> getEmployees(int page, int limit);
}
