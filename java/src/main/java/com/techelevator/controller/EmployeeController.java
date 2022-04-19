package com.techelevator.controller;


import com.techelevator.dao.EmployeeDao;
import com.techelevator.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }
}
