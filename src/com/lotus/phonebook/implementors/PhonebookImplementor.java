package com.lotus.phonebook.implementors;

import java.sql.SQLException;
import java.util.Date;

import com.lotus.phonebook.beans.*;
import com.lotus.phonebook.database.*;

public class PhonebookImplementor implements PhonebookInterface{

	private PhonebookOracleInterface POI;
	
	public PhonebookImplementor(){
		POI = new PhonebookOracle();
	}
	
	public void createContact(String name, String contactNo, Date bday, String code, boolean vip) {
		Contact newContact = new Contact();
		Company newCompany = new Company();		
		newContact.setName(name);
		newContact.setContactNo(contactNo);
		newContact.setBirthdate(bday);
		newContact.setIsVip(vip);
		newCompany.setCode(code);
		POI.insertContact(newContact, newCompany);		
	}

	public void listAllContacts() {
		POI.retrieveContacts();
	}

	@Override
	public void showContact(String name) {
		Contact tempCont = new Contact();
		Company tempComp = new Company();
		try {
			POI.retrieveSpecificContact(tempCont, tempComp,name);
			System.out.println("[CONTACT FOUND]");
			System.out.println("Name: " + tempCont.getName());
			System.out.println("Contact No: " + tempCont.getContactNo());
			System.out.println("Birthday: " + tempCont.getBirthdate());
			System.out.println("Is VIP: " + tempCont.getIsVip());
			System.out.println("Company Name: " + tempComp.getName());
			System.out.println("Company Code: " + tempComp.getCode());
			System.out.println("Company Descprition: " + tempComp.getDescription());
			System.out.println();
		} catch (SQLException e) {
			System.out.println("[SYSTEM MSG] No contact match found.\n");
		} finally {
			POI.closeDatabaseConnection();
		}
	}

	@Override
	public void searchContactByQuery(String query) {
		POI.retrieveContactByQuery(query);
		
	}

	@Override
	public void deleteContact(String name) {
		POI.deleteContact(name);
		
	}

	@Override
	public void updateContact(String name, String contactNo) {
		Contact newContact = new Contact();
		newContact.setName(name);
		newContact.setContactNo(contactNo);
		POI.updateContact(newContact);
	}
}
