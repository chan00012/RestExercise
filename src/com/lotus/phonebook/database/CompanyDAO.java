package com.lotus.phonebook.database;

import java.util.List;

import com.lotus.phonebook.beans.Company;


public interface CompanyDAO {
	
	Company retrieveCompanyByCode(String code);
	Company retrieveCompanyById(long id);


}
