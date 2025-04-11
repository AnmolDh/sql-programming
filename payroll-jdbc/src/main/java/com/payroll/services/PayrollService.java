package com.payroll.services;

import com.payroll.dtos.EmployeePayrollDto;
import com.payroll.dtos.PayrollAnalysisDto;
import com.payroll.exceptions.PayrollServiceException;
import com.payroll.mappings.ToEmployeePayrollDto;
import com.payroll.mappings.ToPayrollAnalysisDto;

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

        try (Connection conn = DbService.getInstance().getConnection()){
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

        try (Connection conn = DbService.getInstance().getConnection()){
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

        try (Connection conn = DbService.getInstance().getConnection()){
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


    public static List<EmployeePayrollDto> getEmployeesByDateRange(Date start, Date end) throws PayrollServiceException {
        List<EmployeePayrollDto> employeePayrolls = new ArrayList<>();

        String query = """
        SELECT * FROM employee e
        JOIN department d ON e.dept_id = d.dept_id
        LEFT JOIN contact c ON e.id = c.employee_id
        LEFT JOIN payroll p ON e.id = p.employee_id
        WHERE e.start_date BETWEEN ? AND ?
    """;

        try (Connection conn = DbService.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, start);
            stmt.setDate(2, end);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employeePayrolls.add(ToEmployeePayrollDto.map(rs));
                }
            }

        } catch (Exception e) {
            throw new PayrollServiceException("Error retrieving employees by date range: " + e.getMessage());
        }

        return employeePayrolls;
    }


    public static List<PayrollAnalysisDto> getPayrollAnalysisByGender() throws PayrollServiceException {
        List<PayrollAnalysisDto> analysisList = new ArrayList<>();

        String query = """
        SELECT gender,
               SUM(salary) AS total_salary,
               AVG(salary) AS average_salary,
               MIN(salary) AS min_salary,
               MAX(salary) AS max_salary,
               COUNT(*) AS employee_count
        FROM employee e
        JOIN payroll p ON e.id = p.employee_id
        GROUP BY gender
    """;

        try (Connection conn = DbService.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                analysisList.add(ToPayrollAnalysisDto.map(rs));
            }

        } catch (SQLException e) {
            throw new PayrollServiceException("Error fetching payroll analysis: " + e.getMessage());
        }

        return analysisList;
    }


}
