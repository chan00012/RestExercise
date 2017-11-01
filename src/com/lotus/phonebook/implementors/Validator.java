package com.lotus.phonebook.implementors;

import com.lotus.phonebook.customeexceptions.*;

public final class Validator {

	private Validator() {
	}
	
	public static void validateName(String name) throws NameException {
		name = name.toLowerCase();
		checkIfNameHaveSpecChar(name);
		checkNameLength(name);
	}
	
	private static void checkIfNameHaveSpecChar(String name) throws NameException {
		for(int i=0; i<name.length(); i++) {
			if(name.charAt(i)<'a' || name.charAt(i)>'z') {
				throw new NameException("[SYSTEM MSG] Name must not contain special characters.\n");
			}
		}
	}
	
	private static void checkNameLength(String name) throws NameException {
		if(name.length()>30) {
			throw new NameException("[SYSTEM MSG] Name exceeded 30 characters. You entered "
									+ name.length() + " characters.\n");
		}
	}

	public static void validateContactNo(String contactNo) throws NumberException {
		checkIfNumber(contactNo);
		checkNumberLength(contactNo);
	}
	
	private static void checkIfNumber(String contactNo) throws NumberException {
		for(int i=0; i<contactNo.length(); i++) {
			if(contactNo.charAt(i) < '0' || contactNo.charAt(i) > '9') {
				throw new NumberException("[SYSTEM MSG] Contact number must only contain numbers.\n");
			}
		}
	}

	private static void checkNumberLength(String contactNo) throws NumberException {
		if(contactNo.length()>11) {
			throw new NumberException("[SYSTEM MSG] You exceeded 11 characters. You entered " + 
										contactNo.length() + " characters.\n");
		}
	}
}
