

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentDNEExceptionTest {

	/* 
	 * Not testing removeEmployee method as this method only removes the specified empID from departments that exist.
	 */
	
	@Test
	void addEmployeetoDepartmentTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		
		// Try to add an existing employee (id=0) to a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.addEmployeeToDepartment(0, "Enineering");
		});
	}
	
	@Test
	void removeEmployeeFromDepTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		
		// Try to remove an existing employee (id=0) from a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.removeEmployeeFromDepartment(0, "Enineering");
		});
	}
	
	@Test
	void removeEmployeeFromDepBoolTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		ems.addEmployee("Andrew", Employee.Gender.MALE);
		
		// Try to remove an existing employee (id=0) from a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.removeEmployeeFromDepartment(0, "Enineering", false);
		});
	}
	
	@Test
	void changeDepartmentnameTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// Try to change the name of a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.changeDepartmentName("Engineering", "Sales");
		});
	}
	
	@Test
	void changeDepartmentTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		Department sales = new Department("Sales");
		
		// Try to change the data of a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.changeDepartment("Engineering", sales);
		});
	}
	
	@Test
	void removeDepartmentTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// Try to remove a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.removeDepartment("Engineering");
		});
	}
	
	@Test
	void removeDepartmentBoolTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// Try to remove a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.removeDepartment("Engineering", false);
		});
	}
	
	@Test
	void getDepartmentTest() {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		// Try to get data from a department that doesn't exist
		assertThrows(EMSDepartmentDoesNotExistException.class, () -> {
			ems.getDepartment("Engineering");
		});
	}
}
