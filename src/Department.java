
import java.util.ArrayList;

public class Department {

	Department(String name)
	{
		this.name = name;
		employees = new ArrayList<Integer>();
	}
	
	@Override
	public String toString() {
		return "Department [name=" + name + ", employees=" + employees + "]";
	}

	// also used as id
	String name;
	
	public String getName() {
		return name;
	}

	ArrayList<Integer> employees;
}
