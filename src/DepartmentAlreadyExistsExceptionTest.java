

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentAlreadyExistsExceptionTest {

	@Test
	void addDepartmentStringTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addDepartment("Engineering");
		
		// Try to add a department that already exists
		assertThrows(EMSDepartmentExistsException.class, () -> {
			ems.addDepartment("Engineering");
		});
	}

	@Test
	void addDepartmentObjectTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		Department engr = new Department("Engineering");
		ems.addDepartment("Engineering");
		
		// Try to add a department that already exists
		assertThrows(EMSDepartmentExistsException.class, () -> {
			ems.addExistingDepartment(engr);
		});
	}
}
