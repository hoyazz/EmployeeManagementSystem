

public class EMSDepartmentExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2994931235466053867L;
	
	public EMSDepartmentExistsException(String dep)
	{
		super("The department " + dep + " already exists");
	}

}
