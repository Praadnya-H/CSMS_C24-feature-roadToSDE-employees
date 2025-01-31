package com.cars24.csms.services.impl;

import com.cars24.csms.data.dao.impl.AppUserDaoImpl;
import com.cars24.csms.data.dao.impl.EmployeeDaoImpl;
import com.cars24.csms.data.entities.EmployeeEntity;
import com.cars24.csms.data.req.CreateEmployeeReq;
import com.cars24.csms.data.req.UpdateEmployeeReq;
import com.cars24.csms.data.res.ApiResponse;
import com.cars24.csms.data.res.GetEmployeeRes;
import com.cars24.csms.exceptions.UserServiceException;
import com.cars24.csms.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDaoImpl employeeDao;
    private final AppUserDaoImpl appUserDetailsDao;

    @Override
    public ResponseEntity<ApiResponse> createEmployee(CreateEmployeeReq createEmployeeReq) {
        log.info("[createEmployee] IN SERVICE {}",createEmployeeReq);
        ApiResponse apiResponse = new ApiResponse();
        boolean userExists = appUserDetailsDao.checkIfUserExists(createEmployeeReq.getEmail());
        log.info("[createEmployee] IN SERVICE userExists: {}",userExists);
        if(userExists){
            boolean userInEmployee = employeeDao.checkIfEmailExists(createEmployeeReq.getEmail());
            log.info("[createEmployee] IN SERVICE userInEmployee: {}",userInEmployee);

            if(!userInEmployee){
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Profile creation Successful!");
                apiResponse.setData(null);
                apiResponse.setService("APP_USER - " + HttpStatus.OK.value());
                int userId = appUserDetailsDao.getUserId(createEmployeeReq.getEmail());
                employeeDao.createEmployee(createEmployeeReq, userId);
            }
            else {
                throw new UserServiceException("Profile already exists!");
            }
        }
        else {
            throw new UserServiceException("User does not exist!");
        }
//        employeeDao.createEmployee(createEmployeeReq);
        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse> getEmployee(int employee_id) {
//        if(!employeeDao.checkIfIdExists(employee_id)){
//            throw new UserServiceException("User does not exist!");
//        }
//        else{

        GetEmployeeRes getEmployeeRes = employeeDao.getEmployee(employee_id);
        log.info("[getEmployee] IN SERVICE: {}",getEmployeeRes);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Employee profile retrieved successfully!");
        apiResponse.setService("EMP_USER-"+HttpStatus.OK.value());

        Map<String, Object> empData = new HashMap<>();
        empData.put("name",getEmployeeRes.getName());
        empData.put("phone",getEmployeeRes.getPhone());
        empData.put("email",getEmployeeRes.getEmail());
        empData.put("role",getEmployeeRes.getRole());
        empData.put("salary",getEmployeeRes.getSalary());
        apiResponse.setData(empData);
        return ResponseEntity.ok().body(apiResponse);
//            return employeeDao.getEmployee(employee_id);
//        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteEmployee(int employee_id) {
        employeeDao.deleteEmployee(employee_id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Employee profile deleted successfully!");
        apiResponse.setService("EMP_USER-"+HttpStatus.OK.value());
        apiResponse.setData(null);

        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse> updateEmployee(int employee_id, UpdateEmployeeReq updateEmployeeReq) {
        employeeDao.updateEmployee(employee_id,updateEmployeeReq);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Employee profile updated successfully!");
        apiResponse.setService("EMP_USER-"+HttpStatus.OK.value());
        apiResponse.setData(null);

        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse> getEmployees(int page, int limit) {
        Page<EmployeeEntity> employeeEntityPage = employeeDao.getEmployees(page,limit);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("employees", employeeEntityPage.getContent());
        responseData.put("totalElements", employeeEntityPage.getTotalElements());
        responseData.put("totalPages", employeeEntityPage.getTotalPages());
        responseData.put("currentPage", employeeEntityPage.getNumber());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Employee profile updated successfully!");
        apiResponse.setService("EMP_USER-"+HttpStatus.OK.value());
        apiResponse.setData(responseData);

        return ResponseEntity.ok().body(apiResponse);

    }


}
