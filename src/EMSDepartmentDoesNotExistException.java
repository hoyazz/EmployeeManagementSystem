

public class EMSDepartmentDoesNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3984739620917632891L;
	
	public EMSDepartmentDoesNotExistException(String dep)
	{
		super("The department " + dep + " does not exist");
	}

}
