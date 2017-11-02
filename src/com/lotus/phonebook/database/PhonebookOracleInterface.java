package com.lotus.phonebook.database;

import java.sql.SQLException;

import com.lotus.phonebook.beans.*;

public interface PhonebookOracleInterface {

	void insertContact(Contact contact, Company company);

	void retrieveContacts();
	
	void retrieveSpecificContact(Contact contact, Company company, String name) throws SQLException;
	
	void closeDatabaseConnection();
	
	void retrieveContactByQuery(String query);
	
	void deleteContact(String query);

	void updateContact(Contact contact);
}
