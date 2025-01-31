package com.cars24.csms.data.repositories;

import com.cars24.csms.data.entities.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    boolean existsByEmail(String email);
    Page<EmployeeEntity> findAll(Pageable pageable);
}
