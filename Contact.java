
public abstract class Contact {
	private String name;
	private String email;
	private String phone;

	public Contact(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone(){
		 return "("+ phone.substring(0,3) + ")" + phone.substring(3,6) + "-"+phone.substring(6);
	 }
	 
	 public void setPhone (String phone){
		this.phone = phone;
	 }
	 	 
	 public abstract void printContactInfo();
	 
	 //Rather than throwing an exception from the setters, I created these
	 //methods so I could validate names, emails & phone numbers BEFORE constructing
	 //a new instance of Contact.
	 
	 public static void validateName(String name) throws InvalidNameException{
		 if (name.equals("") || name.isEmpty()){
			 throw new InvalidNameException("Every Contact must have a name. Please try again.");
		 }
	 }
	 
	 public static void validateEmail(String email) throws InvalidEmailException{
		 if (email.indexOf('@') < 0){
			 throw new InvalidEmailException("It must contain an @ character. Please try again.");
		 }
		 if (email.indexOf('@') != email.lastIndexOf('@')){
			 throw new InvalidEmailException("It cannot contain multiple @ characters. Please try again.");
		 }
		 if (email.indexOf('.') < 0){
			 throw new InvalidEmailException("It must contain a dot (.). Please try again.");
		 }
		 if (email.indexOf('@') > email.lastIndexOf('.')){
			 throw new InvalidEmailException("The dot (.) must come after the @ character. Please try again.");
		 }
	 }
	 
	 public static void validatePhone(String phone) throws InvalidPhoneNumberException{
		 if (phone.length() != 10){
			 throw new InvalidPhoneNumberException(phone + " is not 10 digits. Please try again.");
		 }
	 }	 
}
