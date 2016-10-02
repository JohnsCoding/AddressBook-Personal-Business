
public class InvalidPhoneNumberException extends Exception {
	
	public InvalidPhoneNumberException (String message){
		super ("Invalid Phone Number: " + message);
	}

}
