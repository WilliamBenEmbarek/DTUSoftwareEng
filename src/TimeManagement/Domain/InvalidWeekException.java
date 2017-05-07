package TimeManagement.Domain;

/**
 * Created by William Ben Embarek on 07/05/2017.
 */
public class InvalidWeekException extends Exception {
	private String oprS;
	public InvalidWeekException(String message) {
		super(message);
		oprS = message;
	}
	public Object getOperation (){
		if(oprS.equals("The week is invalid.")){
			return "change week";
		}
		else {
			return null;
		}
	}
}
