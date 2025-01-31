package com.cars24.csms.controllers;

import com.cars24.csms.data.req.CreateEmployeeReq;
import com.cars24.csms.data.req.UpdateEmployeeReq;
import com.cars24.csms.data.res.ApiResponse;
import com.cars24.csms.services.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @PostMapping("/create")
     public ResponseEntity<ApiResponse> createEmployee(@Valid @RequestBody CreateEmployeeReq createEmployeeReq){
        log.info("[createEmployee] IN CONTROLLER : {}",createEmployeeReq);
//        CreateEmployeeRes createEmployeeRes = new CreateEmployeeRes();
        return employeeService.createEmployee(createEmployeeReq);

     }

     @GetMapping("/profile/{id}")
    public ResponseEntity<ApiResponse> getEmployee(@Valid @PathVariable("id") int employee_id){
        log.info("[getEmployee] IN CONTROLLER");
//        ApiRes getEmployeeRes = employeeService.getEmployee(employee_id).getBody();
        return employeeService.getEmployee(employee_id);
     }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@Valid @PathVariable("id") int employee_id){
        log.info("[deleteEmployee] IN CONTROLLER: {}",employee_id);
        return employeeService.deleteEmployee(employee_id);
     }

     @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeReq updateEmployeeReq, @PathVariable("id") int employee_id ){
         log.info("[updateEmployee] IN CONTROLLER: {}",employee_id);
         return employeeService.updateEmployee(employee_id, updateEmployeeReq);
     }

     @GetMapping("/profiles")
    public ResponseEntity<ApiResponse> getEmployees( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit){
         log.info("[getEmployees] IN CONTROLLER: {},{}",page,limit);
         return employeeService.getEmployees(page,limit);
     }

}
