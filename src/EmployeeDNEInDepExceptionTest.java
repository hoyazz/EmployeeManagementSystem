

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeDNEInDepExceptionTest {

	/*
	 * Not testing removeEmployee as it only looks into departments where the employee exists
	 * Not testing removeDepartment as it doen't matter if an employee is in the department.
	 */
	
	@Test
	void removeEmployeeFromDepTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addDepartment("Engineering");
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		
		// Try to remove an employee from a department they are not in
		assertThrows(EMSEmployeeDoesNotExistInDepartmentException.class, () -> {
			ems.removeEmployeeFromDepartment(0, "Engineering");
		});
	}
	
	@Test
	void removeEmployeeFromDepBoolTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addDepartment("Engineering");
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		
		// Try to remove an employee from a department they are not in
		assertThrows(EMSEmployeeDoesNotExistInDepartmentException.class, () -> {
			ems.removeEmployeeFromDepartment(0, "Engineering", false);
		});
	}

}
