package com.lotus.phonebook.implementors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotus.phonebook.beans.*;
import com.lotus.phonebook.customeexceptions.NameException;
import com.lotus.phonebook.database.*;


public class PhonebookImplementor implements PhonebookInterface {

	@Override
	public void createContact(String name, String contactNo, Date birthday, String companyCode, boolean vip) {
		ContactDAO contactDao = new ContactOJDBC();
		CompanyDAO companyDao = new CompanyOJDBC();
		Contact contact = new Contact();
		Company company = companyDao.retrieveCompanyByCode(companyCode);
		
		contact.setName(name);
		contact.setContactNo(contactNo);
		contact.setBirthdate(birthday);
		contact.setIsVip(vip);
		contact.setCompany_id(company.getId());
		
		contactDao.createContact(contact);
	}
	
	public void displayContactWithCompany() {
		List<ContactCompany> cclist = listContactWithCompany();
		
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","NAME","CONTACT NO.", "BIRTHDAY", "IS VIP",
				"COMPANY NAME", "COMPANY CODE", "COMPANY DESCPRIPTION");
		for(ContactCompany cc: cclist) {
			Contact contact = cc.getContact();
			Company company = cc.getCompany();
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",contact.getName(),contact.getContactNo(), 
					contact.getBirthdate(),contact.getIsVip(),company.getName(),company.getCode(), company.getDescription());
		}				
	}
	
	public void displayContactWithCompany(ContactCompany contactCompany) {
		List<ContactCompany> cclist = new ArrayList<>();
		cclist.add(contactCompany);
		
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","NAME","CONTACT NO.", "BIRTHDAY", "IS VIP",
				"COMPANY NAME", "COMPANY CODE", "COMPANY DESCPRIPTION");
		for(ContactCompany cc: cclist) {
			Contact contact = cc.getContact();
			Company company = cc.getCompany();
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",contact.getName(),contact.getContactNo(), 
					contact.getBirthdate(),contact.getIsVip(),company.getName(),company.getCode(), company.getDescription());
		}				
	}
	
	public void displayContactWithCompany(String query) throws NameException {
		List<ContactCompany> cclist = listContactWithCompany(query);
		
		if(cclist.isEmpty()) {
			throw new NameException("[SYSTEM MSG]: No results found.");
			
		}
		
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","NAME","CONTACT NO.", "BIRTHDAY", "IS VIP",
				"COMPANY NAME", "COMPANY CODE", "COMPANY DESCPRIPTION");
		for(ContactCompany cc: cclist) {
			Contact contact = cc.getContact();
			Company company = cc.getCompany();
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",contact.getName(),contact.getContactNo(), 
					contact.getBirthdate(),contact.getIsVip(),company.getName(),company.getCode(), company.getDescription());
		}
		
	}
	
	public void deleteContact(String name) throws NameException {
		ContactDAO contactDao = new ContactOJDBC();
		ContactCompany cc = getSpecificContact(name);
		Contact contact = cc.getContact();
		contactDao.deleteContact(contact);
		
	}

	@Override
	public void updateContact(String name, String contactNo, String ccode, boolean vip) throws NameException {
		ContactDAO contactDao = new ContactOJDBC();
		CompanyDAO companyDao = new CompanyOJDBC();
		ContactCompany cc = getSpecificContact(name);
		Contact contact = cc.getContact();
		Company company = companyDao.retrieveCompanyByCode(ccode);
		contact.setContactNo(contactNo);
		contact.setCompany_id(company.getId());
		contact.setIsVip(vip);
		contactDao.updateContact(contact);
		
	}

	public ContactCompany getSpecificContact(String name) throws NameException {
		ContactDAO contactDao = new ContactOJDBC();
		CompanyDAO companyDao = new CompanyOJDBC();
		
		Contact contact = contactDao.retrieveContactByName(name);
		if(contact == null) {
			throw new NameException("[SYSTEM MSG]: No results found.");
		}
		Company company = companyDao.retrieveCompanyById(contact.getCompany_id());
		ContactCompany cc  = new ContactCompany();
		
		cc.setContact(contact);
		cc.setCompany(company);
		
		return cc;
	}

	public List<ContactCompany> listContactWithCompany() {
		ContactDAO contactDao = new ContactOJDBC();
		CompanyDAO companyDao = new CompanyOJDBC();
		List<ContactCompany> cclist = new ArrayList<>();
		List<Contact> clist = new ArrayList<>();
		Company company;
		ContactCompany cc;
		
		clist = contactDao.contactList();
		
		for(Contact contact: clist) {
			cc = new ContactCompany();
			company = companyDao.retrieveCompanyById(contact.getCompany_id());
			cc.setContact(contact);
			cc.setCompany(company);
			cclist.add(cc);
		}
				
		return cclist;
	}
	
	public List<ContactCompany> listContactWithCompany(String query) {
		ContactDAO contactDao = new ContactOJDBC();
		CompanyDAO companyDao = new CompanyOJDBC();
		List<ContactCompany> cclist = new ArrayList<>();
		List<Contact> clist = new ArrayList<>();
		Company company;
		ContactCompany cc;
		
		clist = contactDao.contactListByQuery(query);
		
		for(Contact contact: clist) {
			cc = new ContactCompany();
			company = companyDao.retrieveCompanyById(contact.getCompany_id());
			cc.setContact(contact);
			cc.setCompany(company);
			cclist.add(cc);
		}
				
		return cclist;
	}

}