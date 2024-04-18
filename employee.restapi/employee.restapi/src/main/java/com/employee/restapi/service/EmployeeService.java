package com.employee.restapi.service;


import com.employee.restapi.dto.EmpDto;



public interface EmployeeService {
     EmpDto saveEmployee(EmpDto empDto);


     Double getEmployeeTaxdeduction(Long id);
}
