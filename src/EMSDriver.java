
import java.io.IOException;

public class EMSDriver {

	public static void main(String[] args) {
	
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		Employee emp;
		
		try
		{
			// add employees
			ems.addEmployee("Bob", Employee.Gender.MALE);
			ems.addEmployee("Katherine", Employee.Gender.FEMALE);
			ems.addEmployee("Xorbliaque", Employee.Gender.OTHER);
			
			System.out.print("Employee Data At Start\n\n");
			
			ems.printEmployees();
			
			// add departments
			ems.addDepartment("Human Resources");
			ems.addDepartment("Janitorial");
			ems.addExistingDepartment(new Department("IT"));
			
			System.out.print("\nDepartment Data At Start\n\n");
			ems.printDepartments();
			
			// add employees to those departments
			ems.addEmployeeToDepartment(0, "Human Resources");
			ems.addEmployeeToDepartment(0, "IT");
			ems.addEmployeeToDepartment(1, "Human Resources");
			ems.addEmployeeToDepartment(2, "IT");
			
			System.out.println("\nEmployee Data After Department Assignment\n");
			ems.printEmployees();
			System.out.println("\nDepartment Data After Assignment\n");
			ems.printDepartments();
			
			// checks if employee is in a specific department
			emp = ems.getEmployee(2);
			System.out.println("\nQuerry If An Employee Is In A Department\n");
			System.out.println(emp.name + " is in IT: " + emp.isInDepartment("IT"));
			System.out.println(emp.name + " is in Human Resources: " + emp.isInDepartment("Human Resources"));
			System.out.println(emp.name + " is in Void: " + emp.isInDepartment("Void"));
		
			// update employee information
			emp = ems.getEmployee(0);
			System.out.println("\nChange Bob's Name And Departments\n");
			System.out.println("Before: " + emp);
			emp.name = "Higgins";
			ems.removeEmployeeFromDepartment(emp.id, "Human Resources");
			ems.removeEmployeeFromDepartment(emp.id, "IT");
			ems.addEmployeeToDepartment(emp.id, "Janitorial");
			System.out.println("After: " + emp);
			
			// remove employee
			ems.removeEmployee(0);
		
			// update department
			System.out.println("\nChange Departments and their names\n");
			System.out.println("Before");
			ems.printDepartments();
			ems.changeDepartmentName("IT", "Internet Technology");
			ems.changeDepartment("Human Resources", new Department("Hiring"));
			System.out.println("\nAfter");
			ems.printDepartments();
			
			// remove department
			System.out.println("Remove Departments\n");
			System.out.println("Before");
			ems.printDepartments();
			ems.removeDepartment("Internet Technology");
			ems.removeDepartment("Janitorial");
			System.out.println("\nAfter");
			ems.printDepartments();
			
			// final printout
			System.out.println("\nEmployee Data After Removal\n");
			ems.printEmployees();
			
			// write final data to file
			ems.writeEmployeesToFile();
			ems.writeDepartmentToFile();
			
			ems.removeEverything();
			System.out.println("\nContents written to a file and cleared, now loading");
			
			ems.loadEmployeesFromFile();
			ems.loadDepartmentFromFile();
			
			System.out.println("\nEmployee Data After Save/Load");
			ems.printEmployees();
			System.out.println("\nDepartment Data After Save/Load");
			ems.printDepartments();
			
			
			
		} catch(EMSEmployeeDoesNotExistException e) {
			e.printStackTrace();
		} catch(EMSDepartmentExistsException e) {
			e.printStackTrace();
		} catch(EMSDepartmentDoesNotExistException e) {
			e.printStackTrace();
		} catch(EMSEmployeeDoesNotExistInDepartmentException e) {
			e.printStackTrace();
		} catch(EMSEmployeeAlreadyInDepartmentException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("End of code");
		}
	}

}
