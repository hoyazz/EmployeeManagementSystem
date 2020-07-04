import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class EMSFileIOTest {

	@Test
	void testWriteEmployeesToFile() throws IOException, EMSDepartmentExistsException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeAlreadyInDepartmentException {
		EmployeeManagementSystem iasip = new EmployeeManagementSystem();
		
		iasip.addDepartment("Janitorial");
		iasip.addDepartment("Security");
		iasip.addDepartment("Relations");
		iasip.addDepartment("Business and Funding");
		
		iasip.addEmployee("Charlie", Employee.Gender.MALE);
		iasip.addEmployee("Mac", Employee.Gender.MALE);
		iasip.addEmployee("Dennis", Employee.Gender.MALE);
		iasip.addEmployee("Dee", Employee.Gender.FEMALE);
		iasip.addEmployee("Frank", Employee.Gender.MALE);
		
		iasip.addEmployeeToDepartment(0, "Janitorial");
		iasip.addEmployeeToDepartment(1, "Security");
		iasip.addEmployeeToDepartment(2, "Relations");
		iasip.addEmployeeToDepartment(3, "Relations");
		iasip.addEmployeeToDepartment(4, "Business and Funding");
		
		String string = "";
		
		File file = new File("EmployeeData.txt");
		Scanner scanner = new Scanner(file);
		iasip.writeEmployeesToFile();
		
		string = scanner.nextLine();
		while (scanner.hasNextLine()) {
			string = string + "\n" + scanner.nextLine();
		}
		
		assertEquals("Charlie;0;MALE;Janitorial\nMac;1;MALE;Security\nDennis;2;MALE;Relations\nDee;3;FEMALE;Relations\nFrank;4;MALE;Business and Funding", string);
		
		scanner.close();
	}

	@Test
	void testLoadEmployeesFromFile() throws IOException, FileNotFoundException, EMSDepartmentExistsException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeAlreadyInDepartmentException, EMSEmployeeDoesNotExistInDepartmentException {
		EmployeeManagementSystem tous = new EmployeeManagementSystem();
	
		tous.addDepartment("Management");
		tous.addDepartment("Accounting");
		tous.addDepartment("Sales");
		tous.addDepartment("Reception");
		
		tous.addEmployee("Michael", Employee.Gender.MALE);
		tous.addEmployee("Oscar", Employee.Gender.MALE);
		tous.addEmployee("Angela", Employee.Gender.FEMALE);
		tous.addEmployee("Kevin", Employee.Gender.MALE);
		tous.addEmployee("Dwight", Employee.Gender.MALE);
		tous.addEmployee("Jim", Employee.Gender.MALE);
		tous.addEmployee("Pam", Employee.Gender.FEMALE);
		
		tous.addEmployeeToDepartment(0, "Management");
		tous.addEmployeeToDepartment(1, "Accounting");
		tous.addEmployeeToDepartment(2, "Accounting");
		tous.addEmployeeToDepartment(3, "Accounting");
		tous.addEmployeeToDepartment(4, "Sales");
		tous.addEmployeeToDepartment(5, "Sales");
		tous.addEmployeeToDepartment(6, "Reception");
		
		// Create file to read from
		tous.writeEmployeesToFile();
		
		// Clear data from EMS
		tous.removeDepartment("Management");
		tous.removeDepartment("Accounting");
		tous.removeDepartment("Sales");
		tous.removeDepartment("Reception");
		
		// Load file created earlier to check
		tous.loadEmployeesFromFile();
		
		assertEquals("Employee [name=Michael, id=0, gender=MALE, departments=[Management]]", tous.getEmployee(0).toString());
		assertEquals("Employee [name=Kevin, id=3, gender=MALE, departments=[Accounting]]", tous.getEmployee(3).toString());
		assertEquals("Employee [name=Pam, id=6, gender=FEMALE, departments=[Reception]]", tous.getEmployee(6).toString());
	}

	@Test
	void testWriteDepartmentToFile() throws IOException, EMSDepartmentExistsException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeAlreadyInDepartmentException {
		EmployeeManagementSystem ram= new EmployeeManagementSystem();
		
		ram.addDepartment("Research and Development");
		
		ram.addEmployee("Rick", Employee.Gender.MALE);
		ram.addEmployee("Morty", Employee.Gender.MALE);
		
		ram.addEmployeeToDepartment(0, "Research and Development");
		ram.addEmployeeToDepartment(1, "Research and Development");
		
		String string = "";
		
		File file = new File("DepartmentData.txt");
		Scanner scanner = new Scanner(file);
		ram.writeDepartmentToFile();
		
		string = scanner.nextLine();
		while (scanner.hasNextLine()) {
			string = string + "\n" + scanner.nextLine();
		}
		
		assertEquals("Research and Development:0,1", string);
		
		scanner.close();
	}

	@Test
	void testLoadDepartmentFromFile() throws IOException, FileNotFoundException, EMSDepartmentExistsException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeAlreadyInDepartmentException, EMSEmployeeDoesNotExistInDepartmentException {
		EmployeeManagementSystem himym= new EmployeeManagementSystem();
		
		himym.addDepartment("Design");		
		himym.addDepartment("Finance");
		himym.addDepartment("Legal");		
		himym.addDepartment("Public Relations");
		himym.addDepartment("Risk Management");
		himym.addDepartment("Training");

		himym.addEmployee("Ted", Employee.Gender.MALE);
		himym.addEmployee("Tracy", Employee.Gender.FEMALE);
		himym.addEmployee("Marshall", Employee.Gender.MALE);
		himym.addEmployee("Robin", Employee.Gender.FEMALE);
		himym.addEmployee("Barney", Employee.Gender.MALE);
		himym.addEmployee("Lily", Employee.Gender.FEMALE);
		himym.addEmployee("James", Employee.Gender.MALE);
		
		himym.addEmployeeToDepartment(0, "Design");
		himym.addEmployeeToDepartment(1, "Finance");
		himym.addEmployeeToDepartment(2, "Legal");
		himym.addEmployeeToDepartment(3, "Public Relations");
		himym.addEmployeeToDepartment(4, "Risk Management");
		himym.addEmployeeToDepartment(5, "Training");
		himym.addEmployeeToDepartment(6, "Public Relations");
		
		himym.writeDepartmentToFile();
		
		himym.removeDepartment("Design");
		himym.removeDepartment("Finance");
		himym.removeDepartment("Legal");
		himym.removeDepartment("Public Relations");
		himym.removeDepartment("Risk Management");
		himym.removeDepartment("Training");
		
		himym.loadDepartmentFromFile();
		
		assertEquals("Department [name=Design, employees=[0]]", himym.getDepartment("Design").toString());
		assertEquals("Department [name=Finance, employees=[1]]", himym.getDepartment("Finance").toString());
		assertEquals("Department [name=Public Relations, employees=[3, 6]]", himym.getDepartment("Public Relations").toString());
	}

}
