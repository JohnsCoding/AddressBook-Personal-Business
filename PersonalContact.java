
public class PersonalContact extends Contact {

	private String facebook;
	private String whatsApp;
	
	public PersonalContact(String name, String email, String phone, String facebook, String whatsApp) {
		super(name, email, phone);
		this.facebook = facebook;
		this.whatsApp = whatsApp;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}
	
	 public void printContactInfo(){
		 System.out.println("\nName:  " + this.getName());
		 System.out.println("Email: " + this.getEmail());
		 System.out.println("Phone: " + this.getPhone());
		 System.out.println("Facebook: " + facebook);
		 System.out.println("WhatsApp: " + whatsApp);
	 }
	
	
	
}
