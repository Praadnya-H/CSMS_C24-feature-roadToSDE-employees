package com.cars24.csms.data.dao;

import com.cars24.csms.data.entities.EmployeeEntity;
import com.cars24.csms.data.req.CreateEmployeeReq;
import com.cars24.csms.data.req.UpdateEmployeeReq;
import com.cars24.csms.data.res.GetEmployeeRes;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeDao {

    int createEmployee(CreateEmployeeReq createEmployeeReq, int userId);
    boolean checkIfEmailExists(String email);
    boolean checkIfIdExists(int employee_id);
    GetEmployeeRes getEmployee(int employee_id);
    int deleteEmployee(int employee_id);
    int updateEmployee(int employee_id, UpdateEmployeeReq updateEmployeeReq);
    Page<EmployeeEntity> getEmployees(int page, int limit);
}
