

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeExistsInDepExceptionTest {

	@Test
	void removeEmployeeTest() throws EMSDepartmentExistsException, EMSEmployeeDoesNotExistException, EMSDepartmentDoesNotExistException, EMSEmployeeAlreadyInDepartmentException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		ems.addDepartment("Engineering");
		ems.addEmployeeToDepartment(0, "Engineering");
		
		// Try to add an employee to a department they are already in
		assertThrows(EMSEmployeeAlreadyInDepartmentException.class, () -> {
			ems.addEmployeeToDepartment(0, "Engineering");
		});
	}

}
