import java.io.IOException;

public class EMSFileDriver {

	public static void main(String[] args) {
		
		EmployeeManagementSystem ems = new EmployeeManagementSystem();
		
		try
		{
			ems.loadDepartmentFromFile();
			
			ems.printDepartments();
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("End of code");
		}
	}
	
}
