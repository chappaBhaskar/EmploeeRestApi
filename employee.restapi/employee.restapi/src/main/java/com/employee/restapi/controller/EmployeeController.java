package com.employee.restapi.controller;


import com.employee.restapi.dto.EmpDto;
import com.employee.restapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

@PostMapping("/saveEmployee")
    public ResponseEntity<EmpDto> saveEmployee(@Validated @RequestBody EmpDto empDto){
    EmpDto saveEmployee=employeeService.saveEmployee(empDto);
        return new ResponseEntity<>(saveEmployee,HttpStatus.CREATED);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?> getEmpId(@PathVariable  Long id){

        return new ResponseEntity<Double>(employeeService.getEmployeeTaxdeduction(id),
                HttpStatus.OK);
    }



}
