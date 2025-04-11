package com.payroll.services;

import com.payroll.dtos.EmployeePayrollDto;
import com.payroll.exceptions.PayrollServiceException;
import com.payroll.mappings.ToEmployeePayrollDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    public static List<EmployeePayrollDto> getEmployeePayrolls() throws PayrollServiceException {
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
                employeePayrolls.add(ToEmployeePayrollDto.map(rs));
            }
        }
        catch (Exception e) {
            throw new PayrollServiceException(e.getMessage());
        }

        return employeePayrolls;
    }

    public static EmployeePayrollDto getEmployeePayroll(int employee_id) throws PayrollServiceException {
        EmployeePayrollDto employeePayroll = null;

        String query = """
            SELECT *
            FROM employee e
            JOIN department d ON e.dept_id = d.dept_id
            LEFT JOIN contact c ON e.id = c.employee_id
            LEFT JOIN payroll p ON e.id = p.employee_id
            WHERE e.id = ?
        """;

        try (Connection conn = DbService.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employee_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employeePayroll = ToEmployeePayrollDto.map(rs);
                break;
            }
        }
        catch (Exception e) {
            throw new PayrollServiceException(e.getMessage());
        }

        return employeePayroll;
    }


    public static void updateEmployeeSalary(String name, double salary) throws PayrollServiceException {
        String query = "UPDATE payroll SET salary=? WHERE payroll_id=(SELECT id FROM employee WHERE name=?)";

        try (Connection conn = DbService.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, salary);
            stmt.setString(2, name);

            stmt.executeUpdate();
            System.out.println("Employee Salary Updated");
        }
        catch (Exception e) {
            throw new PayrollServiceException(e.getMessage());
        }
    }
}
