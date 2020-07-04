
import java.util.ArrayList;

public class Employee {
	
	public enum Gender {
		MALE, FEMALE, OTHER
	}
	
	Employee(String name, int id, Gender gender)
	{
		this.name = name;
		this.id = id;
		this.gender = gender;
		departments = new ArrayList<String>();
	}
	
	public String name;
	int id;
	public Gender gender;
	
	ArrayList<String> departments;

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", gender=" + gender + ", departments=" + departments + "]";
	}
	
	public ArrayList<String> getDepartments()
	{
		return departments;
	}
	
	public boolean isInDepartment(String depname)
	{
		return departments.contains(depname);
	}
	
	public boolean noDepartment()
	{
		return departments.isEmpty();
	}
	
	public Integer getID()
	{
		return id;
	}
}
