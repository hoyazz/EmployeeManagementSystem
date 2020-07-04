

public class EMSEmployeeDoesNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4843467063076872061L;

	public EMSEmployeeDoesNotExistException(int empID)
	{
		super("The employee of ID " + empID + " does not exist");
	}
}
