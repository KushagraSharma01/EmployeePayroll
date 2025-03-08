package com.example.EmployeePayroll.interfaces;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {

    public EmployeeDTO get(Long id) ;

    public EmployeeDTO create(EmployeeDTO newEmp) ;

    public EmployeeDTO edit(EmployeeDTO emp, Long id) ;

    public String delete(Long id);

    public String clear();
}
