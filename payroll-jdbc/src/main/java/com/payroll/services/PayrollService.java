package com.payroll.services;

import com.payroll.dtos.EmployeePayrollDto;
import com.payroll.entities.Contact;
import com.payroll.entities.Department;
import com.payroll.entities.Employee;
import com.payroll.entities.Payroll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    public static List<EmployeePayrollDto> getEmployeePayrolls() {
        List<EmployeePayrollDto> employeePayrolls = new ArrayList<>();

        String query = """
            SELECT *
            FROM employee e
            JOIN department d ON e.dept_id = d.dept_id
            LEFT JOIN contact c ON e.id = c.employee_id
            LEFT JOIN payroll p ON e.id = p.employee_id
        """;

        try (Connection conn = DbService.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Department department = new Department(rs.getInt("dept_id"), rs.getString("dept_name"));
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDate("start_date"), department.getDept_id());
                Contact contact = new Contact(rs.getInt("contact_id"), rs.getString("phone"), rs.getString("email"), rs.getString("address"), employee.getId());
                Payroll payroll = new Payroll(rs.getInt("payroll_id"),
                        rs.getDouble("basic_pay"),
                        rs.getDouble("deductions"),
                        rs.getDouble("taxable_pay"),
                        rs.getDouble("income_tax"),
                        rs.getDouble("net_pay"),
                        rs.getDouble("salary"),
                        employee.getId());

                employeePayrolls.add(new EmployeePayrollDto(employee, payroll, contact, department));
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return employeePayrolls;
    }
}
