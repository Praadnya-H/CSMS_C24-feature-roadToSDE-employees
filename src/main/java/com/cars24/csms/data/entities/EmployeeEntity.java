package com.cars24.csms.data.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "employees")
@Entity
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    private int employee_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false, unique = true)
    @JsonManagedReference
    private AppUserDetailsEntity appUserDetailsEntity;

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                '}';
    }
}
