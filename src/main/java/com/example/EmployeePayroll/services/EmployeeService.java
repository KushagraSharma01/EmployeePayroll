package com.example.EmployeePayroll.services;

import com.example.EmployeePayroll.controller.EmployeeController;
import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.entities.EmployeeEntity;
import com.example.EmployeePayroll.interfaces.IEmployeeService;
import com.example.EmployeePayroll.repositories.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {



    @Autowired
    EmployeeRepository employeeRepository;

    ObjectMapper obj = new ObjectMapper();

    public EmployeeDTO get(Long id) throws Exception{

        EmployeeEntity empFound = employeeRepository.findById(id).orElseThrow(()->
        {
            log.error("Cannot find employee with id {}", id);
            return new RuntimeException("Cannot find employee with given id");
        });

        EmployeeDTO empDto = new EmployeeDTO(empFound.getName(), empFound.getSalary());
        empDto.setId(empFound.getId());

        log.info("Employee DTO send for id: {} is : {}", id, obj.writeValueAsString(empDto));

        return empDto;

    }

    public EmployeeDTO create(EmployeeDTO newEmp) throws Exception{

        EmployeeEntity newEntity = new EmployeeEntity(newEmp.getName(), newEmp.getSalary());

        employeeRepository.save(newEntity);

        log.info("Employee saved in db: {}", obj.writeValueAsString(newEntity));

        EmployeeDTO emp = new EmployeeDTO(newEntity.getName(), newEntity.getSalary());

        emp.setId(newEntity.getId());

        log.info("Employee DTO sent: {}", obj.writeValueAsString(emp));

        return emp;
    }

    public EmployeeDTO edit(EmployeeDTO emp, Long id)throws Exception{
        //finding employee
        EmployeeEntity foundEmp =  employeeRepository.findById(id).orElseThrow(()->
        {
            log.error("Cannot find employee with id : {}", id);
            return new RuntimeException("cannot find employee with given id");
        });

        //updating details
        foundEmp.setName(emp.getName());
        foundEmp.setSalary(emp.getSalary());

        //saving in database
        employeeRepository.save(foundEmp);

        log.info("Employee saved after editing in db is : {}", obj.writeValueAsString(foundEmp));

        //creating dto to return
        EmployeeDTO employeeDTO = new EmployeeDTO(foundEmp.getName(), foundEmp.getSalary());
        employeeDTO.setId(foundEmp.getId());


        return employeeDTO;

    }

    public String delete(Long id){

        EmployeeEntity foundEmp = employeeRepository.findById(id).orElseThrow(()->
        {
            log.error("Cannot find user with id : {}", id);
            return new RuntimeException("cannot find user with given id");
        });

        employeeRepository.delete(foundEmp);

        return "Employee Deleted";

    }


}
