package com.example.EmployeePayroll.interfaces;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {

    public EmployeeDTO get(Long id) throws Exception;

    public EmployeeDTO create(EmployeeDTO newEmp) throws Exception;

    public EmployeeDTO edit(EmployeeDTO emp, Long id) throws Exception;

    public String delete(Long id);


}
