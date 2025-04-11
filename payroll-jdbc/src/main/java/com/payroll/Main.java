package com.payroll;

import com.payroll.dtos.EmployeePayrollDto;
import com.payroll.exceptions.PayrollServiceException;
import com.payroll.services.PayrollService;

import java.util.List;

public class Main {
//    UC 1
    public static void main(String[] args) {
        try{
            // UC 2
            List<EmployeePayrollDto> employeePayroll =  PayrollService.getEmployeePayrolls();
            for (EmployeePayrollDto employeePayrollDto : employeePayroll) {
                System.out.println(employeePayrollDto);
            }


            // UC 3
            PayrollService.updateEmployeeSalary("Bob Smith", 5000.0);


        }
        catch (PayrollServiceException e){
            System.err.println(e.getMessage());
        }
    }
}