package com.techelevator.dao;


import com.techelevator.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Component
public class JdbcEmployeeDao implements EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employee_id, first_name, last_name, title FROM employee";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(results.getInt("employee_id"));
            employee.setFirstName(results.getString("first_name"));
            employee.setLastName(results.getString("last_name"));
            employee.setTitle(results.getString("title"));
            employees.add(employee);
        }
        return employees;
    }
}
