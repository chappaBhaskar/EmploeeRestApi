package com.employee.restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmpDto {

    private Long employeeid;
    @NotNull (message = " Required Firstname ")
    private String firstname;
    @NotNull (message = " Required Lastname")
    private String lastname;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotNull (message = " Required Email")
    private String email;
    @NotBlank(message = "Required Phone no  ")
    private String phonenumber;
    @NotNull(message = " Required Date of joining ")
    private LocalDate joindate;
    @NotNull(message = " Required Employee Salary ")
    private Double salary;

}
