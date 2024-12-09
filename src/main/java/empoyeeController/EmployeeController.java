package empoyeeController;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import employeeEntity.Employee;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    @PostMapping("/store")
    public ResponseEntity<String> storeEmployeeDetails(@Valid @RequestBody Employee employee) {
       
        return ResponseEntity.ok("Employee details stored successfully!");
    }
    
    
    @PostMapping("/calculate-tax")
    public ResponseEntity<Map<String, Object>> calculateTax(@RequestBody Employee employee) {
        // Step 1: Calculate total yearly salary based on DOJ
        LocalDate currentDate = LocalDate.now();
        LocalDate financialYearStart = LocalDate.of(currentDate.getYear(), 4, 1);
        LocalDate financialYearEnd = LocalDate.of(currentDate.getYear() + 1, 3, 31);

        double yearlySalary = calculateYearlySalary(employee.getDateOfJoining(), employee.getSalary(), financialYearStart, financialYearEnd);

        // Step 2: Calculate tax
        double taxAmount = calculateTaxAmount(yearlySalary);

        // Step 3: Calculate cess if applicable
        double cessAmount = yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;

        // Step 4: Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("employeeId", employee.getEmployeeId());
        response.put("firstName", employee.getFirstName());
        response.put("lastName", employee.getLastName());
        response.put("yearlySalary", yearlySalary);
        response.put("taxAmount", taxAmount);
        response.put("cessAmount", cessAmount);

        return ResponseEntity.ok(response);
    }

    private double calculateYearlySalary(LocalDate doj, double monthlySalary, LocalDate fyStart, LocalDate fyEnd) {
        if (doj.isAfter(fyEnd)) {
            return 0; // Joined after financial year
        }
        if (doj.isBefore(fyStart)) {
            doj = fyStart; // Adjust DOJ to financial year start
        }

        long daysWorked = ChronoUnit.DAYS.between(doj, fyEnd) + 1;
        long monthsWorked = daysWorked / 30;
        long partialDays = daysWorked % 30;

        double partialMonthSalary = (partialDays / 30.0) * monthlySalary;
        return (monthsWorked * monthlySalary) + partialMonthSalary;
    }

    private double calculateTaxAmount(double yearlySalary) {
        double tax = 0;

        if (yearlySalary > 1000000) {
            tax += (yearlySalary - 1000000) * 0.20;
            yearlySalary = 1000000;
        }
        if (yearlySalary > 500000) {
            tax += (yearlySalary - 500000) * 0.10;
            yearlySalary = 500000;
        }
        if (yearlySalary > 250000) {
            tax += (yearlySalary - 250000) * 0.05;
        }

        return tax;
    }
    
    
    
    @PostMapping("/calculate-tax-update")
    public ResponseEntity<Map<String, Object>> calculateTaxUpdate(@RequestBody Employee employee) {
        // Current financial year range
        LocalDate currentDate = LocalDate.now();
        LocalDate financialYearStart = LocalDate.of(currentDate.getYear(), 4, 1);
        LocalDate financialYearEnd = LocalDate.of(currentDate.getYear() + 1, 3, 31);

        // Step 1: Calculate total yearly salary
        double yearlySalary = calculateYearlySalary(
            employee.getDateOfJoining(),
            employee.getSalary(),
            financialYearStart,
            financialYearEnd
        );

        // Step 2: Calculate tax amount
        double taxAmount = calculateTaxAmount(yearlySalary);

        // Step 3: Calculate cess amount
        double cessAmount = yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;

        // Step 4: Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("employeeId", employee.getEmployeeId());
        response.put("firstName", employee.getFirstName());
        response.put("lastName", employee.getLastName());
        response.put("yearlySalary", yearlySalary);
        response.put("taxAmount", taxAmount);
        response.put("cessAmount", cessAmount);

        return ResponseEntity.ok(response);
    }

    
    
    
    
    
    
    
    
    
    
    

}
