import java.util.Scanner;

public class AddressBookEntryValidation {

	static Scanner userEntry = new Scanner(System.in);

	public static String validateName () {
		while(true) {
	       	System.out.print("Name: "); 
	       	String validName = (userEntry.nextLine());
	       		// Validate name in series of string  format
	       		if (validName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*")) {
	        		return validName;
	           	} 
	        	System.out.println("Invalid Name");
	        }	
	}

	public static String validateAddress () {
		while(true) {
	       	System.out.print("Address: "); 
	       	String validAddress = (userEntry.nextLine());
	           	// Validate address with digit and string series format
	       		if (validAddress.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
	        		return validAddress;
	           	} 
	        	System.out.println("Invalid Address");
	        }	
	}
	
	public static String validateTelNo () {
		while(true) {
	       	System.out.print("Phone No.: "); 
	       	// Validate telephone number with 7-10 digits format
	       	String validTelNo = (userEntry.nextLine());
	           	if (validTelNo.matches("\\d{7,10}")) {
	        		return validTelNo;
	           	} 
	        	System.out.println("Invalid Number");
	        }	
	}

	public static String validateEmail () {
		while(true) {
	       	System.out.print("Email: "); 
	       	String validEmail = (userEntry.nextLine());
	       		// Validate Email with series of string, including "@" and ".".
	       		if (validEmail.matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
	        		return validEmail;
	           	} 
	        	System.out.println("Invalid Email Address");
	        }	
	}
}