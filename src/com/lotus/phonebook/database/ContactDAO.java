package com.lotus.phonebook.database;

import java.util.List;

import com.lotus.phonebook.beans.Contact;

public interface ContactDAO {

	void createContact(Contact c);
	void updateContact(Contact c);
	void deleteContact(Contact c);
	List<Contact> contactList();
	List<Contact> contactListByQuery(String query);
	Contact retrieveContactByName(String name);
}
