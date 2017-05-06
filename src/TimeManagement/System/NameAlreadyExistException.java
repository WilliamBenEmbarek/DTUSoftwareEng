package TimeManagement.System;

/**
 * Created by Emil on 18/04/2017.
 */
public class NameAlreadyExistException extends Exception{
    private String oprS;

    public NameAlreadyExistException(String errorMsg) {
        super(errorMsg);
        oprS = errorMsg;
    }

    public Object getOperation() {
        if(oprS.equals("This name already exist.")){
            return "Change name";
        }
        else {
            return null;
        }
    }

}
