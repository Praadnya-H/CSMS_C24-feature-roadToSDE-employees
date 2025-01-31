package com.cars24.csms.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "AppUserDetails")
@Entity
@Data
public class AppUserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean is_active;

    @OneToOne(mappedBy = "appUserDetailsEntity")
    @JsonBackReference
    private EmployeeEntity employeeEntity;

    @Column(name = "user_type")
    private String user_type;

    @Override
    public String toString() {
        return "AppUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
