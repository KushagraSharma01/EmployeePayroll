package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.interfaces.IEmployeeService;
import com.example.EmployeePayroll.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeeController {

    ObjectMapper obj = new ObjectMapper();

    @Autowired
    IEmployeeService iEmployeeService;

    //UC2 --> CRUD operations on employee database through REST API's
    @GetMapping("/get/{id}")
    public EmployeeDTO get(@PathVariable Long id) throws Exception{
        log.info("Employee tried to get with id: {}", id);
        return iEmployeeService.get(id);
    }

    @PostMapping("/create")
    public EmployeeDTO create(@RequestBody EmployeeDTO newEmp) throws Exception{
        log.info("Employee tried to create with body: {}", obj.writeValueAsString(newEmp));
        return iEmployeeService.create(newEmp);
    }

    @PutMapping("/edit/{id}")
    public EmployeeDTO edit(@RequestBody EmployeeDTO emp, @PathVariable Long id) throws Exception{
        log.info("Employee tried to edit with id : {} and body : {}", id, obj.writeValueAsString(emp));
        return iEmployeeService.edit(emp, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        log.info("Employee tried to delete with id: {}", id);
        return iEmployeeService.delete(id);
    }



}
