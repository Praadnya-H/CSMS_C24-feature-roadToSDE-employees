package com.cars24.csms.data.dao.impl;

import com.cars24.csms.data.dao.EmployeeDao;
import com.cars24.csms.data.entities.AppUserDetailsEntity;
import com.cars24.csms.data.entities.EmployeeEntity;
import com.cars24.csms.data.enums.EmployeeRole;
import com.cars24.csms.data.repositories.EmployeeRepository;
import com.cars24.csms.data.req.CreateEmployeeReq;
import com.cars24.csms.data.req.UpdateEmployeeReq;
import com.cars24.csms.data.res.GetEmployeeRes;
import com.cars24.csms.exceptions.UserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeDaoImpl implements EmployeeDao {

    private final EmployeeRepository employeeRepository;

    @Override
    public int createEmployee(CreateEmployeeReq createEmployeeReq, int userId) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployee_id(0);
        employeeEntity.setName(createEmployeeReq.getName());
        employeeEntity.setPhone(createEmployeeReq.getPhone());
        employeeEntity.setEmail(createEmployeeReq.getEmail());
        employeeEntity.setRole(String.valueOf(createEmployeeReq.getRole()));
        employeeEntity.setSalary(createEmployeeReq.getSalary());
        employeeEntity.setIs_active(true);

        AppUserDetailsEntity appUserDetailsEntity = new AppUserDetailsEntity();
        appUserDetailsEntity.setId(userId);

        employeeEntity.setAppUserDetailsEntity(appUserDetailsEntity);

        employeeRepository.save(employeeEntity);

        log.info("[createEmployee] IN DAO");
        return 0;
    }

    public boolean checkIfEmailExists(String email){
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean checkIfIdExists(int employee_id) {
        return employeeRepository.existsById(employee_id);

    }

    @Override
    public GetEmployeeRes getEmployee(int employee_id) {
//        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeEntity employeeEntity = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new UserServiceException("User does not exist!"));
        log.info("[getEmployee] IN DAO employeeEntity: {}",employeeEntity);

        GetEmployeeRes getEmployeeRes = new GetEmployeeRes();
        getEmployeeRes.setName(employeeEntity.getName());
        getEmployeeRes.setPhone(employeeEntity.getPhone());
        getEmployeeRes.setEmail(employeeEntity.getEmail());
        getEmployeeRes.setSalary(employeeEntity.getSalary());
        getEmployeeRes.setRole(EmployeeRole.valueOf(employeeEntity.getRole()));
        log.info("[getEmployee] IN DAO getEmployeeRes: {}",getEmployeeRes);
        return getEmployeeRes;
    }

    @Override
    public int deleteEmployee(int employee_id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new UserServiceException("User does not exist!"));
        log.info("[deleteEmployee] IN DAO employeeEntity: {}",employeeEntity);

        employeeEntity.setIs_active(false);
        employeeRepository.save(employeeEntity);
        return 0;
    }

    @Override
    public int updateEmployee(int employee_id, UpdateEmployeeReq updateEmployeeReq) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new UserServiceException("User does not exist!"));

        log.info("[updateEmployee] IN DAO employeeEntity: {}",employeeEntity);

        if(updateEmployeeReq.getName() != null){
            employeeEntity.setName(updateEmployeeReq.getName());
        }
        if(updateEmployeeReq.getPhone() != null){
            employeeEntity.setPhone(updateEmployeeReq.getPhone());
        }
        if(updateEmployeeReq.getSalary() != null){
            employeeEntity.setSalary(updateEmployeeReq.getSalary());
        }
        if(updateEmployeeReq.getRole() != null){
            employeeEntity.setRole(String.valueOf(updateEmployeeReq.getRole()));
        }
        if(updateEmployeeReq.getCurrent_email() != null){
            employeeEntity.setEmail(updateEmployeeReq.getCurrent_email());
            AppUserDetailsEntity appUserDetailsEntity = employeeEntity.getAppUserDetailsEntity();
            appUserDetailsEntity.setUsername(updateEmployeeReq.getCurrent_email());
            employeeEntity.setAppUserDetailsEntity(appUserDetailsEntity);
        }
        employeeRepository.save(employeeEntity);
        return 0;
    }

    @Override
    public Page<EmployeeEntity> getEmployees(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return employeeRepository.findAll(pageable);
    }


}
