

public class EMSEmployeeDoesNotExistInDepartmentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517254150229630704L;

	public EMSEmployeeDoesNotExistInDepartmentException(int empID, String depName)
	{
		super("The employee of ID " + empID + " already does not exist within department " + depName);
	}
}
