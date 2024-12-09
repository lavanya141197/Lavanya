package employeeEntity;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Employee {
	
	 @NotNull(message = "Employee ID is mandatory")
	    private Integer employeeId;

	    @NotBlank(message = "First name is mandatory")
	    private String firstName;

	    @NotBlank(message = "Last name is mandatory")
	    private String lastName;

	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is mandatory")
	    private String email;

	    @NotBlank(message = "Phone number is mandatory")
	    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	    private String phoneNumber;

	    @NotNull(message = "Date of joining is mandatory")
	    private LocalDate dateOfJoining;

	    @Min(value = 1000, message = "Salary must be at least 1000")
	    private Double salary;

		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public LocalDate getDateOfJoining() {
			return dateOfJoining;
		}

		public void setDateOfJoining(LocalDate dateOfJoining) {
			this.dateOfJoining = dateOfJoining;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}
//
//		public double getMonthlySalary() {
//			return 0;
//		}

}
