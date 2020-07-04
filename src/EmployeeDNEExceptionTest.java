

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeDNEExceptionTest {
	
/* 	No need to test for the EMSEmployeeDoesNotExistException for the changeDepartment methods.
 *  These functions only use employee data that already exists for every case. We, the users,
 *  do not manipulate the data in a way where this exception gets thrown
 */
	
	@Test
	void removeEmployeeTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// WTrying to remove an employee that DNE
		assertThrows(EMSEmployeeDoesNotExistException.class, () -> {
			ems.removeEmployee(1);
		});
	}
	
	@Test
	void getEmployeeTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// Trying to get data from an employ that DNE
		assertThrows(EMSEmployeeDoesNotExistException.class, () -> {
			ems.getEmployee(1);
		});
	}

	@Test
	void addEmployeeToDepartmentTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		// Throwing EMSDepartmentExistsException as we are not testing for that
		ems.addDepartment("Engineering");
		
		// Trying to add an employee that DNE into a department
		assertThrows(EMSEmployeeDoesNotExistException.class, () -> {
			ems.addEmployeeToDepartment(1, "Engineering");
		});
	}
	
	@Test
	void removeEmployeeFromDepartmentTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		// Throwing EMSDepartmentExistsException as we are not testing for that
		ems.addDepartment("Engineering");
		
		// Trying to add an employee that DNE to an existing department
		assertThrows(EMSEmployeeDoesNotExistException.class, () -> {
			ems.removeEmployeeFromDepartment(1, "Engineering");
		});
	}
	
	@Test
	void removeEmployeeFromDepartmentBoolTest() throws EMSDepartmentExistsException {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		// Throwing EMSDepartmentExistsException as we are not testing for that
		ems.addDepartment("Engineering");
		
		// Trying to add an employee that DNE to an existing department
		assertThrows(EMSEmployeeDoesNotExistException.class, () -> {
			ems.removeEmployeeFromDepartment(1, "Engineering", false);
		});
	}
	
	
}
