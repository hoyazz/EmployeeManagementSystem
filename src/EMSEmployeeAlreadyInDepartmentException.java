

public class EMSEmployeeAlreadyInDepartmentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -797198284256529777L;
	
	public EMSEmployeeAlreadyInDepartmentException(int empID, String depName)
	{
		super("The employee of ID " + empID + " already exists within department " + depName);
	}
}
