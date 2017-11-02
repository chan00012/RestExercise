package com.lotus.phonebook.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lotus.phonebook.beans.Company;
import com.lotus.phonebook.beans.Contact;

public class PhonebookOracle implements PhonebookOracleInterface {
	
	private Connection connection;
	
	public PhonebookOracle(){
		connection = null;
	}

	public void connectPhonebookDatabase() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "phonebook","password");
				connection.setAutoCommit(false);
				System.out.println("[SYSTEM MSG] Connected to phonebook database.\n");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Can't establish connection.\n");
				closeDatabaseConnection();
				e.printStackTrace();
			}
	}
	
	@Override
	public void insertContact(Contact contact, Company company)  {
		String nestedQuery = "(SELECT company_id FROM company WHERE company_code = '" + company.getCode() + "')";
		String sql = "INSERT INTO contact VALUES(contid_sq.NEXTVAL,?,?,?,"+nestedQuery+",?)";
		java.sql.Date sqlDate = new java.sql.Date(contact.getBirthdate().getTime());
		connectPhonebookDatabase();
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, contact.getName());
				ps.setDate(2, sqlDate);
				ps.setBoolean(3, contact.getIsVip());
				ps.setString(4, contact.getContactNo());
				ps.executeUpdate();
				connection.commit();
				System.out.println("[SYSTEM MSG]" + contact.getName() + " is added on phonebook database.\n");
			} catch (SQLException e) {
				System.out.println("[SYSTEM MSG] Cant add. " + contact.getName() + " already exist on database.\n");
				e.printStackTrace();
			} finally {
				closeDatabaseConnection();
			}	
	}

	public void closeDatabaseConnection() {
		try {
			connection.close();
			System.out.println("[SYSTEM MSG] Database closed.\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retrieveContacts() {
		connectPhonebookDatabase();
		String sql = "SELECT a.contact_name, a.contact_no, a.contact_bday, a.contact_vip, b.company_code, b.company_name, b.company_description "
				+ "FROM contact a "
				+ "FULL JOIN company b "
				+ "ON a.company_id = b.company_id";
		
		displayHeader();
		objectExtractionLoop(sql);
	
	}

	public void retrieveSpecificContact(Contact contact, Company company, String name) throws SQLException {
		connectPhonebookDatabase();
		String sql = "SELECT a.contact_name, a.contact_no, a.contact_bday, a.contact_vip, b.company_code, b.company_name, b.company_description "
				+ "FROM contact a "
				+ "FULL JOIN company b "
				+ "ON a.company_id = b.company_id "
				+ "WHERE a.contact_name = '"+ name +"'";
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);	
		rs.next();
		extractDataToObj(rs, contact, company);
		rs.close();
	}

	@Override
	public void retrieveContactByQuery(String query) {
		connectPhonebookDatabase();
		String sql = "SELECT a.contact_name, a.contact_no, a.contact_bday, a.contact_vip, b.company_code, b.company_name, b.company_description "
				+ "FROM contact a "
				+ "FULL JOIN company b "
				+ "ON a.company_id = b.company_id "
				+ "WHERE a.contact_name LIKE '%" + query + "%' OR a.contact_no LIKE '%" + query +"%'" ;
		
		displayHeader();
		objectExtractionLoop(sql);
	}

	private void displayHeader() {
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","NAME","CONTACT NO.", "BIRTHDAY", "IS VIP",
				"COMPANY NAME", "COMPANY CODE", "COMPANY DESCPRIPTION");
	}

	private void objectExtractionLoop(String sql) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			Contact tempCont = new Contact();
			Company tempComp = new Company();
			while(rs.next()) {				
				extractDataToObj(rs, tempCont, tempComp);				
				System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",tempCont.getName(),tempCont.getContactNo(),tempCont.
						getBirthdate(),tempCont.getIsVip(),tempComp.getName(),tempComp.getCode(),tempComp.getDescription());
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDatabaseConnection();
		}
	}

	private void extractDataToObj(ResultSet rs, Contact tempCont, Company tempComp) throws SQLException {
		tempCont.setName(rs.getString("contact_name"));
		tempCont.setContactNo(rs.getString("contact_no"));
		tempCont.setBirthdate(rs.getDate("contact_bday"));
		tempCont.setIsVip(rs.getBoolean("contact_vip"));
		tempComp.setName(rs.getString("company_name"));
		tempComp.setCode(rs.getString("company_code"));
		tempComp.setDescription(rs.getString("company_description"));
	}

	@Override
	public void deleteContact(String name) {
		connectPhonebookDatabase();
		String sql = "DELETE FROM contact WHERE contact_name = '"+ name + "'";
		System.out.println(sql);
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("[SYSTEM MSG] " + name + " is deleted.\n");
			connection.commit();
		} catch (SQLException e) {
			System.out.println("[SYSTEM MSG] no matching contact found.\n");
			e.printStackTrace();
		} finally {
			closeDatabaseConnection();
		}
		
	}

	@Override
	public void updateContact(Contact contact) {
		connectPhonebookDatabase();
		String sql = "UPDATE contact SET contact_no = ? WHERE contact_name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, contact.getContactNo());
			ps.setString(2, contact.getName());
			ps.executeUpdate();
			connection.commit();
			System.out.println("[SYSTEM MSG] " + contact.getName() + " is successfully updated.");
		} catch (SQLException e) {
			System.out.println("[SYSTEM MSG] Account already exist.");
			e.printStackTrace();
		}
	}

	

}
