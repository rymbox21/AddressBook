
public class AddressBook {
    private AddressBookEntry list[];
    
    // variable size addressbook
    public AddressBook(int size) {    
        list = new AddressBookEntry[size];
    }
    
    public AddressBook() {
        // if size not specified, default no. of entries is 100
        list = new AddressBookEntry[100];        
    }
    
    public boolean addAddressBookEntry(AddressBookEntry entry) {
        for( int i = 0; i < list.length; i++ ) {
            // find next available empty slot
            if(list[i] == null) {
                list[i] = entry;
                return true;
            }
        }
        // return false if no slot available, addressbook is full
        return false;
    }
    
    public boolean deleteAddressBookEntry(int recordNo) {
        int validRecord = 0;
        // record number is greater than the index number by 1
        if( recordNo > 0 && (recordNo-1) < list.length ) {
            for(int i = 0; i < list.length; i++ ) {
                if( list[i]!=null ) {
                    validRecord += 1;
                }
                
                if(validRecord == recordNo ) {
                	list[i] = null;
                	return true; // return true if deletion is successful
                }
            } 
        }
        return false;
    }
    
    // find a specific record number using its record number
    public AddressBookEntry findAddressBookEntryByRecordNo(int recordNo) {
        if( recordNo > 0 && (recordNo-1) < list.length ) {
            if( list[recordNo-1]!= null ) {
                return list[recordNo-1];
            }
        } 
        return null;
    }
    
    public boolean updateAddressBookEntry(int recordNo, AddressBookEntry entry) {
    	// Added record number validation for null entry
    	if((recordNo > 0 && (recordNo-1) < list.length) && (list[recordNo-1] != null)) {
    		 list[recordNo-1] = entry;
   			 return true;
    	}
    	return false;
    }
    
    public AddressBookEntry[] getAllEntries() {
        return list;
    }
    
}
