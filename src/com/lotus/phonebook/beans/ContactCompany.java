package com.lotus.phonebook.beans;

public class ContactCompany {
	
	Contact contact;
	Company company;
	
	public ContactCompany(){
		contact = new Contact();
		company = new Company();
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
}
