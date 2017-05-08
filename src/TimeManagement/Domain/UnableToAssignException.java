package TimeManagement.Domain;

/**
 * Created by Emil on 08/05/2017.
 */
public class UnableToAssignException extends Exception {
	private String oprS;

	public UnableToAssignException(String errorMsg) {
		super(errorMsg);
		oprS = errorMsg;
	}

	public Object getOperation() {
		if (oprS.equals("Unable to assign exception.")) {
			return "Try again";
		} else {
			return null;
		}
	}
}
