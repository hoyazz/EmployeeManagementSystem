
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.io.*;

public class EmployeeManagementSystem {
	
	private HashMap<Integer, Employee> employees;
	private HashMap<String, Department> departments;
	private ArrayList<Integer> employeesNoDepartment;
	private int next_id = 0;
	
	// UNIVERSAL METHODS
	
	public EmployeeManagementSystem()
	{
		employees = new HashMap<Integer, Employee>();
		departments = new HashMap<String, Department>();
		employeesNoDepartment = new ArrayList<Integer>();
	}
	
	public void removeEverything()
	{
		employees.clear();
		departments.clear();
		employeesNoDepartment.clear();
		next_id = 0;
	}
	
	// EMPLOYEE METHODS
	
	public void printEmployees()
	{
		for(Map.Entry<Integer, Employee> emp : employees.entrySet())
			System.out.println(emp);
	}
	
	public int addEmployee(String name, Employee.Gender gender)
	{
		Employee emp = new Employee(name, next_id, gender);
		Integer id = next_id;
		
		employees.put(id, emp);
		employeesNoDepartment.add(id);
		++next_id;
		
		return id.intValue();
	}
	
	private void addEmployee(String name, int id, Employee.Gender gender, String[] departments)
	{
		Employee emp = new Employee(name, id, gender);

		if (departments[0].equals("")) { // if the employee is not assigned to a department
			employeesNoDepartment.add(id);
		} else { 
			for (String dep : departments) {
				emp.departments.add(dep);
			}
		}
		employees.put(id, emp);
		
		// sets last_id as the max id entered + 1
		if (next_id - 1 != Math.max(next_id - 1, id)) {
			next_id = id;
			++next_id;
		}
	}

	public void removeEmployee(int empID) throws EMSEmployeeDoesNotExistException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistInDepartmentException
	{
		Employee emp = getEmployee(empID);
		for(int i = 0; i < emp.departments.size(); ++i)
			removeEmployeeFromDepartment(empID, emp.departments.get(i));
		employees.remove(empID);
	}
	
	public Employee getEmployee(int empID) throws EMSEmployeeDoesNotExistException
	{
		if(employees.containsKey(empID))
			return employees.get(empID);
		throw new EMSEmployeeDoesNotExistException(empID);
	}
	
	public ArrayList<Integer> getEmployeesWithNoDepartment()
	{
		return employeesNoDepartment;
	}
	
	public void addEmployeeToDepartment(int empID, String depName) throws EMSEmployeeDoesNotExistException, EMSDepartmentDoesNotExistException, EMSEmployeeAlreadyInDepartmentException
	{
		if(!employees.containsKey(empID))
			throw new EMSEmployeeDoesNotExistException(empID);
		
		Department dep = getDepartment(depName);
		
		if(dep.employees.contains(empID))
			throw new EMSEmployeeAlreadyInDepartmentException(empID, depName);
		dep.employees.add(empID);
		
		Employee emp = employees.get(empID);
		if(emp.noDepartment())
			employeesNoDepartment.remove(emp.getID());
		emp.departments.add(depName);
	}
	
	public void removeEmployeeFromDepartment(int empID, String depName) throws EMSEmployeeDoesNotExistException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistInDepartmentException
	{
		// if you're removing an employee from a department, instead
		// of removing the department or removing the employee directly,
		// then you probably don't intend on removing them
		removeEmployeeFromDepartment(empID, depName, false);
	}
	
	public void removeEmployeeFromDepartment(int empID, String depName, boolean removeUnusedEmployees) throws EMSEmployeeDoesNotExistException, EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistInDepartmentException
	{
		if(!employees.containsKey(empID))
			throw new EMSEmployeeDoesNotExistException(empID);
		
		Department dep = getDepartment(depName);
		// forces dep.employees.remove(Object) instead of dep.employees.remove(index)
		Integer empIDObject = empID;
		
		if(!dep.employees.contains(empID))
			throw new EMSEmployeeDoesNotExistInDepartmentException(empID, depName);
		dep.employees.remove(empIDObject);
		
		Employee emp = employees.get(empID);
		emp.departments.remove(depName);
		if(emp.noDepartment())
		{
			if(removeUnusedEmployees)
				removeEmployee(empID);
			else
				employeesNoDepartment.add(emp.getID());
		}
			
	}
	
	// DEPARTMENT METHODS
	
	public void printDepartments()
	{
		for(Map.Entry<String, Department> dep : departments.entrySet())
			System.out.println(dep);
	}
	
	public void addDepartment(String depName) throws EMSDepartmentExistsException
	{
		addExistingDepartment(new Department(depName));
	}
	
	public void addDepartment(String depName, String[] employee)
	{
		Department dep = new Department(depName);
		if (!employee[0].equals("")) {
			for (String emp : employee) {
				dep.employees.add(Integer.valueOf(emp));
			}
		}
		departments.put(depName, dep);
	}
	
	public void addExistingDepartment(Department dep) throws EMSDepartmentExistsException
	{
		if(departments.containsKey(dep.name))
			throw new EMSDepartmentExistsException(dep.name);
		departments.put(dep.name, dep);
	}
	
	public void changeDepartmentName(String origDepName, String newDepName) throws EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException
	{
		Department oldDep = getDepartment(origDepName);
		changeEmployeeDepartmentInternal(oldDep, newDepName);
		departments.remove(origDepName);
		departments.put(newDepName, oldDep);
	}
	
	public void changeDepartment(String origDepName, Department newDep) throws EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException
	{
		Department dep = getDepartment(origDepName);
		changeEmployeeDepartmentInternal(dep, newDep.name, newDep);
		departments.remove(origDepName);
		departments.put(newDep.name, newDep);
	}
	
	public void removeDepartment(String depName) throws EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeDoesNotExistInDepartmentException
	{
		removeDepartment(depName, true);
	}
	
	public void removeDepartment(String depName, boolean removeUnusedEmployees) throws EMSDepartmentDoesNotExistException, EMSEmployeeDoesNotExistException, EMSEmployeeDoesNotExistInDepartmentException
	{
		Department dep = getDepartment(depName);
		Employee emp;
		for(int i = 0; i < dep.employees.size(); ++i)
		{
			emp = getEmployee(dep.employees.get(i));
			emp.departments.remove(depName);
			if(removeUnusedEmployees && emp.noDepartment())
				removeEmployee(emp.id);
		}
		departments.remove(depName);
	}
	
	public Department getDepartment(String depName) throws EMSDepartmentDoesNotExistException
	{
		if(departments.containsKey(depName))
			return departments.get(depName);
		throw new EMSDepartmentDoesNotExistException(depName);
	}
	
	// PRIVATE METHODS

	private void changeEmployeeDepartmentInternal(Department origDep, String newDepName) throws EMSEmployeeDoesNotExistException
	{
		changeEmployeeDepartmentInternal(origDep, newDepName, null);
	}
	
	private void changeEmployeeDepartmentInternal(Department origDep, String newDepName, Department newDep) throws EMSEmployeeDoesNotExistException
	{
		Employee emp;
		if(newDep != null) // this may be some sort of merger...
			newDep.employees.addAll(origDep.employees);
		
		for(int i = 0; i < origDep.employees.size(); ++i)
		{
			emp = getEmployee(origDep.employees.get(i));
			emp.departments.set(emp.departments.indexOf(origDep.name), newDepName);
		}
	}
	
	// FILE I/O
	
	public void writeEmployeesToFile() throws IOException {
		File file = new File("EmployeeData.txt");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int emp : employees.keySet()) {
			Employee employee = employees.get(emp);
			String department = String.join(",", employee.departments);
			writer.write(String.join(";", employee.name, Integer.toString(employee.id), employee.gender.toString(), department));
			writer.newLine();
		}
		
		writer.flush();
		writer.close();
	}
	
	public void loadEmployeesFromFile() throws IOException, FileNotFoundException {
		BufferedReader reader;
		
		reader = new BufferedReader(new FileReader("EmployeeData.txt"));
		String line;
		String empName;
		int empId;
		Employee.Gender gender;
		String[] depArr;

		while ((line = reader.readLine()) != null) {
			String[] data = line.split(";", 4);
			empName = data[0];
			empId = Integer.valueOf(data[1]);
			gender = Employee.Gender.valueOf(data[2]);
			depArr = data[3].split(",");
			addEmployee(empName, empId, gender, depArr);
		}

		reader.close();
	}
	
	public void writeDepartmentToFile() throws IOException {
		File file = new File("DepartmentData.txt");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (Department dep : departments.values()) {
			ArrayList<String> empID = new ArrayList<String>();
			for (Integer id : dep.employees) {
				empID.add(String.valueOf(id));
			}
			String employee = String.join(",", empID);
			writer.write(String.join(":", dep.getName(), employee));
			writer.newLine();
		}
		
		writer.flush();
		writer.close();
	}
	
	public void loadDepartmentFromFile() throws IOException, FileNotFoundException {
		BufferedReader reader;
		
		reader = new BufferedReader(new FileReader("DepartmentData.txt"));

		String line;
		String depName;
		String[] employee;

		while ((line = reader.readLine()) != null) {
			String[] data = line.split(":", 2);
			depName = data[0];
			employee = data[1].split(",");
			addDepartment(depName, employee);
		}

		reader.close();
		
	}
}
