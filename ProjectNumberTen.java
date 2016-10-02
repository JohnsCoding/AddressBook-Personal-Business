import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjectNumberTen {

	public static ArrayList<Contact> listOfContacts = new ArrayList<Contact>();

	public static String specializedField[][] = { { "Facebook address", "Whatsapp address" },
												{ "Company", "Linkedin address" } };

	public static void main(String[] args) {

		// optional code for pre-filling address book

		/*
		 * PersonalContact peter = new PersonalContact("Peter Johnson","pete@aol.com","9548786932","pete@facebook","pete@WhatsApp");
		 * PersonalContact george = new PersonalContact("George Samuels","gs@hotmail.com","7894521452","George@facebook","gs@WhatsApp");
		 * BusinessContact paul = new BusinessContact("Paul Roberts","proberts@yahoo.com","8976521425","AT&T","paul@linked.in");
		 * 
		 * listOfContacts.add(peter);
		 * listOfContacts.add(george);
		 * listOfContacts.add(paul);
		 */

		int mainMenuChoice = 0;

		Scanner scan = new Scanner(System.in);

		do {

			mainMenuChoice = runMainMenu(scan);

			switch (mainMenuChoice) {
			case 1:
				addNewContact(scan);
				break;
			case 2:
				editContact(scan);
				break;
			case 3:
				deleteContact(scan);
				break;
			case 4:
				printOutAddressBook();
				break;
			}

		} while (mainMenuChoice != 5);

		scan.close();

	}

	public static int runMainMenu(Scanner s) {

		String intro = (listOfContacts.size() == 1) ?
				"\nThere is currently 1 contact in your address book." :
				"\nThere are currently " + listOfContacts.size() + " contacts in your address book.";

		System.out.println(intro);

		for (Contact c : listOfContacts) {
			System.out.println(c.getName());
		}

		System.out.println("\nWould you like to:");
		System.out.println("\t1. Add a new contact");
		System.out.println("\t2. Edit an existing contact");
		System.out.println("\t3. Delete an existing contact");
		System.out.println("\t4. See all contacts and details");
		System.out.println("\t5. Exit the program");

		int verifiedMenuChoice = verifyMenuChoice(s, 5);

		// limit the user's options when address book is empty
		while (listOfContacts.isEmpty() && (verifiedMenuChoice == 2 || verifiedMenuChoice == 3)) {
			String inactiveOption = (verifiedMenuChoice == 2 ? "edit" : "delete");
			System.out.println("There are no contacts to " + inactiveOption + ". Please select another option.");
			verifiedMenuChoice = verifyMenuChoice(s, 5);
		}

		return verifiedMenuChoice;
	}

	public static void addNewContact(Scanner scan) {

		System.out.println("\nWill this new contact be a:");
		System.out.println("\t1. Personal contact");
		System.out.println("\t2. Business Contact");

		int contactTypeChoice = verifyMenuChoice(scan, 2);

		System.out.println("\nPlease type in the name of the new contact followed by the 'ENTER' key.");
		String n = haveUserEnterName(scan);

		System.out.println("Please type in " + n + "'s email followed by the 'ENTER' key.");
		String e = haveUserEnterEmail(scan);

		System.out.println("Please type in " + n + "'s phone number (10 digits with no spaces or dashes - XXXXXXXXXX) followed by the 'ENTER' key.");
		String p = haveUserEnterPhoneNumber(scan);

		System.out.println("Optional: Please type in " + n + "'s " + specializedField[contactTypeChoice - 1][0] + " followed by the 'ENTER' key.");
		String sf1 = scan.nextLine();

		System.out.println("Optional: Please type in " + n + "'s " + specializedField[contactTypeChoice - 1][1] + " followed by the 'ENTER' key.");
		String sf2 = scan.nextLine();

		System.out.println("\nOk, " + n + " has been added to your address book.");

		switch (contactTypeChoice) {
		case 1:
			listOfContacts.add(new PersonalContact(n, e, p, sf1, sf2));
			break;
		case 2:
			listOfContacts.add(new BusinessContact(n, e, p, sf1, sf2));
			break;
		}
	}

	public static void editContact(Scanner s) {

		System.out.println("\nWhose information would you like to edit?");
		int verifiedMenuChoice = createMenuListOfNames(s);

		Contact c = listOfContacts.get(verifiedMenuChoice - 1);

		System.out.println("\nWhat information would you like to change?");
		System.out.println("\t1. Name: " + c.getName());
		System.out.println("\t2. Email: " + c.getEmail());
		System.out.println("\t3. Phone: " + c.getPhone());

		int contactType = 0;

		if (c instanceof PersonalContact) {
			System.out.println("\t4. Facebook: " + ((PersonalContact) c).getFacebook());
			System.out.println("\t5. WhatsApp: " + ((PersonalContact) c).getWhatsApp());
		}

		if (c instanceof BusinessContact) {
			System.out.println("\t4. Company: " + ((BusinessContact) c).getCompany());
			System.out.println("\t5. Linkedin: " + ((BusinessContact) c).getLinkedin());
			contactType = 1;
		}

		System.out.println("\t6. Make no changes and return to the main menu");

		int verifiedEditChoice = verifyMenuChoice(s, 6);

		if (verifiedEditChoice == 6) {
			return;
		}

		String[] field = { "", "name", "email", "phone" };

		String fieldToBeEdited = "";

		if (verifiedEditChoice < 4) {
			fieldToBeEdited = field[verifiedEditChoice];
		} else {
			fieldToBeEdited = specializedField[contactType][verifiedEditChoice - 4];
		}

		System.out.println(
				"\nPlease type in the new " + fieldToBeEdited + " for " + c.getName() + " followed by the 'ENTER' key");

		String newField = "";
		String oldField = "";

		switch (verifiedEditChoice) {
		case 1: // edit name
			oldField = c.getName();
			newField = haveUserEnterName(s);
			c.setName(newField);
			break;

		case 2: // edit email
			oldField = c.getEmail();
			newField = haveUserEnterEmail(s);
			c.setEmail(newField);
			break;

		case 3: // edit phone number
			oldField = c.getPhone();
			newField = haveUserEnterPhoneNumber(s);
			c.setPhone(newField);

			// reassign newField to phone number in formatted form
			newField = listOfContacts.get(verifiedMenuChoice - 1).getPhone();

			break;

		case 4: // edit specialized field #1 (Facebook OR Company)
			if (contactType == 0) {// Contact is Personal
				oldField = ((PersonalContact) c).getFacebook();
				newField = s.nextLine();
				((PersonalContact) c).setFacebook(newField);
			} else {// Contact is Business
				oldField = ((BusinessContact) c).getCompany();
				newField = s.nextLine();
				((BusinessContact) c).setCompany(newField);
			}

			break;

		case 5:// edit specialized field #2 (WhatsApp OR Linkedin)
			if (contactType == 0) {// Contact is Personal
				oldField = ((PersonalContact) c).getWhatsApp();
				newField = s.nextLine();
				((PersonalContact) c).setWhatsApp(newField);
			} else {// Contact is Business
				oldField = ((BusinessContact) c).getLinkedin();
				newField = s.nextLine();
				((BusinessContact) c).setLinkedin(newField);
			}

			break;
		}

		System.out.println("\nOK, the " + fieldToBeEdited + " has been changed.");
		System.out.println(oldField + " has been updated to " + newField + " in your address book.");

	}

	public static void deleteContact(Scanner s) {

		System.out.println("Which contact would you like to delete?");
		int verifiedMenuChoice = createMenuListOfNames(s);

		System.out.println(
				"Are you sure that you want to remove " + listOfContacts.get(verifiedMenuChoice - 1).getName() + "?");
		System.out.println("This action is irreversible.");
		System.out.println("\t1. Yes\n\t2. No");

		int verifiedDeleteChoice = verifyMenuChoice(s, 2);

		if (verifiedDeleteChoice == 1) {
			System.out.println(
					listOfContacts.get(verifiedMenuChoice - 1).getName() + " is no longer in your address book.");
			listOfContacts.remove(verifiedMenuChoice - 1);
		} else {
			System.out.println("Ok, " + listOfContacts.get(verifiedMenuChoice - 1).getName()
					+ " will remain in your address book.");
		}

	}

	public static void printOutAddressBook() {

		System.out.println("\nADDRESS BOOK - " + listOfContacts.size() + " contacts");

		for (Contact c : listOfContacts) {
			c.printContactInfo();
		}
	}

	public static int verifyMenuChoice(Scanner s, int numberOfChoices) {

		int menuChoice = 0;
		boolean integerEnteredSuccessfully = false;

		// verify that the user enters an integer type
		while (!integerEnteredSuccessfully) {
			try {
				menuChoice = s.nextInt();

				// verify that integer is within the bounds of the menu
				while (menuChoice < 1 || menuChoice > numberOfChoices) {
					System.out.println("That is not a valid choice. Please type a number between 1 and "
							+ numberOfChoices + " followed by the 'ENTER' key.");
					menuChoice = s.nextInt();
				}

				integerEnteredSuccessfully = true;
			} catch (InputMismatchException e) {
				System.out.println("That is not a valid choice. You must enter a number between 1 and "
						+ numberOfChoices + " followed by the 'ENTER' key.");
			} finally {
				s.nextLine();// fix scanner int, then String problem
			}
		}

		int verifiedMenuChoice = menuChoice;

		return verifiedMenuChoice;
	}

	public static String haveUserEnterName(Scanner s) {

		String name = "";
		boolean nameIsProperlyEntered = false;

		while (!nameIsProperlyEntered) {
			name = s.nextLine();
			try {
				Contact.validateName(name);
				nameIsProperlyEntered = true;
			} catch (InvalidNameException e) {
				System.out.println(e.getMessage());
			}
		}

		return name;
	}

	public static String haveUserEnterEmail(Scanner s) {

		String email = "";
		boolean emailIsProperlyFormatted = false;

		while (!emailIsProperlyFormatted) {
			email = s.nextLine();
			try {
				Contact.validateEmail(email);
				emailIsProperlyFormatted = true;
			} catch (InvalidEmailException e) {
				System.out.println(e.getMessage());
			}
		}

		return email;
	}

	public static String haveUserEnterPhoneNumber(Scanner s) {

		String phone = "";
		boolean numberIsTenDigits = false;

		while (!numberIsTenDigits) {
			phone = s.nextLine();
			try {
				Contact.validatePhone(phone);
				numberIsTenDigits = true;
			} catch (InvalidPhoneNumberException e) {
				System.out.println(e.getMessage());
			}
		}

		return phone;
	}

	public static int createMenuListOfNames(Scanner s) {

		int counter = 1;

		for (Contact c : listOfContacts) {
			System.out.println("\t " + counter + ". " + c.getName());
			counter++;
		}

		int verifiedMenuChoice = verifyMenuChoice(s, listOfContacts.size());

		return verifiedMenuChoice;
	}
}
