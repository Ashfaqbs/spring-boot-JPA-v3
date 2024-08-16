package com.ashfaq.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashfaq.example.dao.EmployeeRepository;
import com.ashfaq.example.dto.EmployeeDTO;
import com.ashfaq.example.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
            employee.getEmployeeId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getPhoneNumber(),
            employee.getHireDate(),
            employee.getJobId(),
            employee.getSalary(),
            employee.getCommissionPct(),
            employee.getManagerId(),
            employee.getDepartmentId()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(
            employeeDTO.getEmployeeId(),
            employeeDTO.getFirstName(),
            employeeDTO.getLastName(),
            employeeDTO.getEmail(),
            employeeDTO.getPhoneNumber(),
            employeeDTO.getHireDate(),
            employeeDTO.getJobId(),
            employeeDTO.getSalary(),
            employeeDTO.getCommissionPct(),
            employeeDTO.getManagerId(),
            employeeDTO.getDepartmentId()
        );
    }
}
