package com.lotus.phonebook.implementors;

import java.util.Date;

public interface PhonebookInterface {

	void createContact(String name, String contactNo, Date bday, String company_id, boolean vip);
	
	void listAllContacts();
	
	void showContact(String name);
	
	void searchContactByQuery(String query);
	
	void deleteContact(String name);
	
	void updateContact(String name, String contactNo);
}
