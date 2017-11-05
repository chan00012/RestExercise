package com.lotus.phonebook.implementors;

import java.util.Date;
import java.util.List;

import com.lotus.phonebook.beans.ContactCompany;
import com.lotus.phonebook.customeexceptions.NameException;

public interface PhonebookInterface{
	
	void createContact(String name ,String contactNo ,Date birthday ,String companyCode ,boolean vip);
	void displayContactWithCompany();
	void displayContactWithCompany(ContactCompany contactCompany);
	void displayContactWithCompany(String query) throws NameException;
	void deleteContact(String name) throws NameException;
	void updateContact(String name, String contactNo, String ccode, boolean vip) throws NameException;
	List<ContactCompany> listContactWithCompany();
	List<ContactCompany> listContactWithCompany(String query);
	ContactCompany getSpecificContact(String name) throws NameException;
}