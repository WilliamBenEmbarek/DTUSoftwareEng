package TimeManagement.Domain;

/**
 * Created by Emil on 07/05/2017.
 */
public class InvalidInputException extends Exception {
	private String oprS;

	public InvalidInputException(String errorMsg) {
		super(errorMsg);
		oprS = errorMsg;
	}

	public Object getOperation() {
		if (oprS.equals("The input is invalid.")) {
			return "Try again";
		} else {
			return null;
		}
	}
}
