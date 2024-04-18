package com.employee.restapi.service.impl;

import com.employee.restapi.Exception.EmployeeNotFoundException;
import com.employee.restapi.bean.Employee;
import com.employee.restapi.bean.EmployeeTax;
import com.employee.restapi.dto.EmpDto;
import com.employee.restapi.repository.EmployeeRepository;
import com.employee.restapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
   private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmpDto saveEmployee(EmpDto empDto) {
        System.out.println("In Save");
        Employee employee = modelMapper.map(empDto, Employee.class);

        Employee savedEmployee = employeeRepository.save(employee);

        EmpDto    savedEmployeeDto = modelMapper.map(savedEmployee, EmpDto.class);
        return savedEmployeeDto;

    }



    @Override
    public Double getEmployeeTaxdeduction(Long id) {
        Double tax = 0.0;
        Double cess = 0.0;

        Employee empId = employeeRepository.findById(id).orElseThrow(
                ()->new EmployeeNotFoundException("EmployeeNotFound")
        );

        List<EmployeeTax> empTax = new ArrayList<>();
        EmpDto employeeDto = modelMapper.map(empId, EmpDto.class);

        LocalDate employeeJoinDate = employeeDto.getJoindate();
        LocalDate employeeEndingDate =LocalDate.now()  ;
        long totalEmployeeWorkiingMonths = ChronoUnit.MONTHS.between(employeeJoinDate, employeeEndingDate);
        Double totalSalaryOfEmployee = employeeDto.getSalary() *  totalEmployeeWorkiingMonths ;

        if(totalSalaryOfEmployee>2500000) {
            cess=(totalSalaryOfEmployee-2500000)*0.02;
        }

        if (totalSalaryOfEmployee > 250000 && totalSalaryOfEmployee<=500000)
        {

             tax = (totalSalaryOfEmployee-250000)*0.05;

        }
        else if (totalSalaryOfEmployee > 500000 && totalSalaryOfEmployee <= 1000000)
        {
             tax = 12500+(totalSalaryOfEmployee-500000)*0.1;
        }
        else if (totalSalaryOfEmployee > 1000000 )
        {

             tax =112500+(totalSalaryOfEmployee-1000000)*0.2 ;
        }



        double employeeTotTax =tax+cess;


        return employeeTotTax;
    }


}
