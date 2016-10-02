
public class InvalidEmailException extends Exception{
	
	public InvalidEmailException (String message){
		super ("Invalid Email Address: " + message);
	}

}
