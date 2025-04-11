package com.payroll;

import com.payroll.dtos.EmployeePayrollDto;
import com.payroll.services.PayrollService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<EmployeePayrollDto> employeePayroll =  PayrollService.getEmployeePayrolls();

        for (EmployeePayrollDto employeePayrollDto : employeePayroll) {
            System.out.println(employeePayrollDto);
        }

    }
}