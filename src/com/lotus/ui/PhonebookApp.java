package com.lotus.ui;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.lotus.phonebook.customeexceptions.*;
import com.lotus.phonebook.implementors.Validator;

public class PhonebookApp {
	
	public Scanner userInput;
	private boolean menuLoop;
	
	PhonebookApp(){
		userInput = new Scanner(System.in);
	}

	public void printMenu() {
		menuLoop = true;
		while(menuLoop) {
			System.out.println("------------------------------------");
			System.out.println("WELCOME TO PETMALU PHONEBOOK APP");
			System.out.println("[1] List all contacts");
			System.out.println("[2] Show contacts");
			System.out.println("[3] Search a contact");
			System.out.println("[4] Delelte contact");
			System.out.println("[5] Create contact");
			System.out.println("[6] Update contact");
			System.out.println("[0] Exit");
			System.out.println("------------------------------------");
			System.out.print("Your choice ? : ");
			askUserOption();
		}
	}	
	
	public void askUserOption() {
		String option = userInput.nextLine();
		
		if(option.equals("0")) {
			menuLoop = false;
		} else if(option.equals("5")) {
			askContactDetailsAndValidate();

		}
	}
	
	public void askContactDetailsAndValidate() {
		try {
			String name = askNameAndValidate();
			String contactNo = askContactAndValidate();
			Date birthday = askBirthdayAndValidate();
			String companyCode = askCompanyCodeAndValidate();
			boolean vip = askIfVip();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (NameException e) {
			System.out.println(e.getMessage());
		} catch (NumberException e) {
			System.out.println(e.getMessage());
		}	
	}	
	
	public String askNameAndValidate() throws NameException {	
		System.out.println("\n[FULLNAME]");
		System.out.println("Note:");
		System.out.println("1. Name must not contain special characters.");
		System.out.println("2. Name must not exceed 30 characters.\n");
		System.out.print("Please enter your fullname: ");
		String name = userInput.nextLine().trim();
		Validator.validateName(name);
		return name;
	}
	
	public String askContactAndValidate() throws NumberException{	
		System.out.println("\n[CONTACT NUMBER]");
		System.out.println("Note:");
		System.out.println("1. Contact number must only consist of numbers.");
		System.out.println("2. Contact number must not be greater that 11 digits.\n");
		System.out.print("Please enter your contact no: ");
		String contactNo = userInput.nextLine().trim();
		Validator.validateContactNo(contactNo);
		return contactNo;
	}
	
	public Date askBirthdayAndValidate() throws ParseException {	
		System.out.println("\n[Birthday]");
		System.out.println("Note:");
		System.out.println("1. follow this format MM.DD.YY");
		System.out.println("2. You can leave this as blank.\n");
		System.out.print("Please enter your birthday: ");
		
		String inputDate = userInput.nextLine().trim();
		
		if(inputDate.isEmpty()) {
			return null;
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yy");
			Date date = sdf.parse(inputDate);
			return date;
		}
	}
	
	public String askCompanyCodeAndValidate() {	
		System.out.println("\n[Company]");
		System.out.println("Company\t\t Code");
		System.out.println("Betica\t\t BETCA");
		System.out.println("Lotus labs\t LOTUS");
		System.out.println("Petmalu Corp.\t PTMLU");
		System.out.println("Idolo Inc.\t OLODI");
		System.out.print("\nPlease enter your company code: ");
		return userInput.nextLine().trim();
	}

	public boolean askIfVip() {
		System.out.println("\n[VIP]");
		System.out.print("Are you a VIP? (y/n): ");
		String response = userInput.nextLine().trim().toLowerCase();
		if(response.equals("y")) {
			return true;
		} else if(response.equals("n")) {
			return false;
		} else {
			return false;
		}

	}
}
