
import java.util.Scanner;


public class AddressBookApp {
	private static final char ADD_ENTRY      = '1';
	private static final char DELETE_ENTRY   = '2';
	private static final char VIEW_ALLENTRY  = '3';
	private static final char EDIT_ENTRY     = '4';
	private static final char SEARCH_BYNAME  = '5';
	private static final char SEARCH_BYADDR  = '6';
	private static final char SEARCH_BYPHONE  = '7';
	private static final char SEARCH_BYREC   = '8';
	private static final char EXIT           = 'X';	
	
    private static Scanner dataReader;
    private static AddressBook book;

    public static void main(String[] args) {            
        book = new AddressBook(10);
        dataReader = new Scanner(System.in);
        
        boolean lContinue = true;
        while (lContinue) {
            switch (Character.toUpperCase(menu())) {
                case ADD_ENTRY    : addBookEntry(); break;                
                case DELETE_ENTRY : deleteEntry(); break;
                case VIEW_ALLENTRY: viewAllEntries(); break;
                case EDIT_ENTRY   : editEntry(); break;
                case SEARCH_BYNAME: searchEntryByName(); break; 
                case SEARCH_BYADDR: searchEntryByAddress(); break; 
                case SEARCH_BYPHONE: searchEntryByPhone(); break; 
                case SEARCH_BYREC : searchEntryByRecord(); break;
                case EXIT:
                    lContinue = false;
                    break;
                default:
                    System.out.println("\nInvalid Menu option");
            }            
        }
        System.out.println("\nEnd of program...");
    }

    public static char menu() {
        char choice;
        System.out.println("\nAddressBook Menu");
        System.out.println("1. Add Entry");        
        System.out.println("2. Delete Entry");
        System.out.println("3. View all Entries");
        System.out.println("4. Update an Entry");
        System.out.println("5. Search Entry By Name");
        System.out.println("6. Search Entry By Address");
        System.out.println("7. Search Entry By Phone Number");
        System.out.println("8. Search Entry By Record Number");
        System.out.println("X. Exit Program");
        System.out.print("\nSelect Menu Option: ");
        choice = dataReader.nextLine().charAt(0);
        return choice;
    }
    
    public static AddressBookEntry getEntryDetails(AddressBookEntry entry) {
        if( entry == null ) {
            entry = new AddressBookEntry();
        }
        System.out.println();
        entry.setName(AddressBookEntryValidation.validateName());
        entry.setAddress(AddressBookEntryValidation.validateAddress());
        entry.setTelNo(AddressBookEntryValidation.validateTelNo());
        entry.setEmailAdd(AddressBookEntryValidation.validateEmail());

        return entry;
        
      
    }
    
    public static void addBookEntry() {
        AddressBookEntry entry = getEntryDetails(null);
        if( entry != null ) {
            book.addAddressBookEntry(entry);
        }
    }
    
    public static void editEntry() {
        // Edit and update a single record entry
    	// 1. Ask the user what record number to edit
    	// 2. Search an entry using its record number
    	// 3. if the record is found, display the current entry
    	// 4. Allow the user to edit the attributes/fields of the entry 
    	// 5. if a particular field is change, it must update the filed in the array
    	// 6. if left blank, the original field will not be change.
    	System.out.print("\nSearch[Record Number]: ");
    	int recordNo = Integer.parseInt(dataReader.nextLine());
    		
     	if (book.updateAddressBookEntry(recordNo, book.findAddressBookEntryByRecordNo(recordNo))) {
    		
     		// Store previous entries
    		AddressBookEntry oldEntry = book.findAddressBookEntryByRecordNo(recordNo);
        	String oldName 		= oldEntry.getName();
        	String oldAddress 	= oldEntry.getAddress();
        	String oldTelNo 	= oldEntry.getTelNo();
        	String oldEmailAdd 	= oldEntry.getEmailAdd();
    		
    		displayEntry(book.findAddressBookEntryByRecordNo(recordNo), recordNo);
    		AddressBookEntry entry = getEntryDetails(book.findAddressBookEntryByRecordNo(recordNo));
    		System.out.println();
    		//Validation for blank entry
    		if (entry.getName().isEmpty()){
    			entry.setName(oldName);
    		}
    		if (entry.getAddress().isEmpty()){
    			entry.setAddress(oldAddress);
    		}
    		if (entry.getTelNo().isEmpty()){
    			entry.setTelNo(oldTelNo);
    		}
    		if (entry.getEmailAdd().isEmpty()){
    			entry.setEmailAdd(oldEmailAdd);
    		}
    			
    		System.out.print("Record Updated.");
    		displayEntry(book.findAddressBookEntryByRecordNo(recordNo), recordNo);
    	} else {
    		System.out.println("Record Number " +recordNo +" not found.");
    	} 
    	//System.out.println("\nUnder construction....");
    }
    
    public static void searchEntryByRecord() {
        // Search an entry using its record no.
        // display "record not found" if such record does not exist.
        // Display all its entry. 
        // Hint: use the method "findAddressBookEntryByRecordNo()" 
        //       from the AddressBook class
    	System.out.print("\nSearch[Record Number]: ");
    	int recordNo = Integer.parseInt(dataReader.nextLine());
    	if (book.findAddressBookEntryByRecordNo(recordNo) != null) {
    		displayEntry(book.findAddressBookEntryByRecordNo(recordNo) , recordNo);
    	} else {
    		System.out.println("Record Number " +recordNo +" not found.");
    	}
    	//System.out.println( book.findAddressBookEntryByRecordNo(recordNo));
  		//System.out.println("\nRecord not found! ");
    	
    	
    	//System.out.println("\nUnder construction....");
    }

    
    public static void deleteEntry() {
        // Delete an entry using its record no.
    	// display "record not found" if such record does not exist.	
    	// Hint: use the method "deleteAddressBookEntry()" 
        //       from the AddressBook class
    	System.out.print("\nPlease enter Record No.: ");
    	
    	int recordNo = Integer.parseInt(dataReader.nextLine());
    	//book.deleteAddressBookEntry(recordNo);
    	if (book.deleteAddressBookEntry(recordNo)) {
    		System.out.println("\nRecord No." +recordNo +" deleted! ");
    	} else {	
    	
    		System.out.println("\nRecord not found! ");
    	}
    	//System.out.println("\nUnder construction....");
    }
    
    // display a single record
    public static void displayEntry(AddressBookEntry entry, int recNo) {        
        System.out.println("\nRecord No. " + recNo);
        System.out.println("Name     : " + entry.getName());
        System.out.println("Address  : " + entry.getAddress());
        System.out.println("Phone No.: " + entry.getTelNo());
        System.out.println("Email    : " + entry.getEmailAdd());
    }
    
    // Search all entries containing name search criteria
    public static void searchEntryByName() {       
        System.out.print("\nSearch[Name]: "); 
        // ensure no extraneous space and search criteria all in lowercase 
        String name = dataReader.nextLine().trim().toLowerCase();   
        
        // get a reference to the Addressbook list
        AddressBookEntry[] list = book.getAllEntries();             
        for( int i = 0; i < list.length; i++ ) {   
            // compare search criteria with every entry 
            if(list[i]!=null && list[i].getName().toLowerCase().contains(name)) {
                 displayEntry(list[i],i+1); 
            }
        }
        System.out.println("No more records follow...");
    }
    
    // Search all entries containing address search criteria
    public static void searchEntryByAddress() {       
        // Search entry by address
    	// search criteria can be any substring inside the address string
    	 System.out.print("\nSearch[Address]: "); 
         // ensure no extraneous space and search criteria all in lowercase 
         String address = dataReader.nextLine().trim().toLowerCase();
    	
         AddressBookEntry[] list = book.getAllEntries();             
         for( int i = 0; i < list.length; i++ ) {   
             // compare search criteria with every entry 
             if(list[i]!=null && list[i].getAddress().toLowerCase().contains(address)) {
                  displayEntry(list[i],i+1); 
             }
         }
         System.out.println("No more records follow...");  	
    	//System.out.println("\nUnder construction....");
    }
    	
    // Search all entries containing address search criteria
    public static void searchEntryByPhone() {       
        // Search all entries by phone number
    	// search criteria can be any substring inside the phone string
    	 System.out.print("\nSearch[Phone Number]: "); 
         // ensure no extraneous space and search criteria all in lowercase 
         String telNo = dataReader.nextLine().trim().toLowerCase();
    	
         AddressBookEntry[] list = book.getAllEntries();             
         for( int i = 0; i < list.length; i++ ) {   
             // compare search criteria with every entry 
             if(list[i]!=null && list[i].getTelNo().toLowerCase().contains(telNo)) {
                  displayEntry(list[i],i+1); 
             }
         }
    	//System.out.println("\nUnder construction....");
    }
    
    public static void viewAllEntries() {
        int validRecords = 0;
        
        // get a reference to the Addressbook list
        AddressBookEntry[] list = book.getAllEntries();
        if( list.length == 0) {
            System.out.println("\nList empty...");
        }
        
        for( int i = 0; i < list.length; i++ ) {
            if( list[i] != null ) {
                displayEntry(list[i],++validRecords);                
            }    
        }
        System.out.println("No more entries to follow...");
    }
}
