
public class BusinessContact extends Contact{
	
	private String company;
	private String linkedin;
	
	public BusinessContact(String name, String email, String phone, String company, String linkedin) {
		super(name, email, phone);
		this.company = company;
		this.linkedin = linkedin;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
	 public void printContactInfo(){
		 System.out.println("\nName:  " + this.getName());
		 System.out.println("Email: " + this.getEmail());
		 System.out.println("Phone: " + this.getPhone());
		 System.out.println("Company: " + company);
		 System.out.println("Linkedin: " + linkedin);
	 }

}
