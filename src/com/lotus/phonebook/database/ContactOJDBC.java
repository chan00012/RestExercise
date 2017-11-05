package com.lotus.phonebook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lotus.phonebook.beans.Company;
import com.lotus.phonebook.beans.Contact;

public class ContactOJDBC implements ContactDAO {
	
	public ContactOJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to establish database connection.");
		}
	}

	public void createContact(Contact c) {
		Connection connection = null;
		PreparedStatement statement = null;
		java.sql.Date sqlDate =  new java.sql.Date(c.getBirthdate().getTime());
		try {
			 connection = getConnection();
			 statement = connection.prepareStatement("INSERT INTO contact VALUES(contid_sq.NEXTVAL,?,?,?,?,?)");
			 statement.setString(1, c.getName());
			 statement.setDate(2, sqlDate);
			 statement.setBoolean(3, c.getIsVip());
			 statement.setLong(4,c.getCompany_id());
			 statement.setString(5, c.getContactNo());
			 statement.executeUpdate();
			 connection.commit();
		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
	}

	public void deleteContact(Contact c) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM contact WHERE contact_name = ?");
			statement.setString(1, c.getName());
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally  {
			closeConnection(connection);
		}
	}
	
	@Override
	public void updateContact(Contact c) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = getConnection();
			statement = connection.
					prepareStatement("UPDATE contact SET contact_no = ?, company_id = ?, contact_vip = ? WHERE contact_name = ?");
			statement.setString(1, c.getContactNo());
			statement.setLong(2, c.getCompany_id());
			statement.setBoolean(3, c.getIsVip());
			statement.setString(4, c.getName());
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally  {
			closeConnection(connection);
		}
	}
			
	public Contact retrieveContactByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Contact contact = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM contact WHERE contact_name = ?");
			statement.setString(1, name);
			
			rs = statement.executeQuery();
			
			while(rs.next()) {
				contact = new Contact();
				contact.setName(rs.getString("contact_name"));
				contact.setBirthdate(rs.getDate("contact_bday"));
				contact.setCompany_id(rs.getLong("company_id"));
				contact.setContactNo(rs.getString("contact_no"));
				contact.setIsVip(rs.getBoolean("contact_vip"));
				contact.setId(rs.getLong("contact_id"));
			}
			rs.close();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	
		return contact;
	}

	public List<Contact> contactList(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Contact> clist = new ArrayList<>();
		Contact contact;
		
		try {
			connection = getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contact");
			
			while(rs.next()) {
				contact = new Contact();
				contact.setName(rs.getString("contact_name"));
				contact.setBirthdate(rs.getDate("contact_bday"));
				contact.setContactNo(rs.getString("contact_no"));
				contact.setIsVip(rs.getBoolean("contact_vip"));
				contact.setId(rs.getLong("contact_id"));
				contact.setCompany_id(rs.getLong("company_id"));
				
				clist.add(contact);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}	
		return clist;
	}
		
	public List<Contact> contactListByQuery(String query){
		query = "%" + query + "%";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Contact> clist = new ArrayList<>();
		Contact contact;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM contact WHERE contact_name LIKE ? OR contact_no LIKE ?");
			statement.setString(1, query);
			statement.setString(2, query);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				contact = new Contact();
				contact.setName(rs.getString("contact_name"));
				contact.setBirthdate(rs.getDate("contact_bday"));
				contact.setContactNo(rs.getString("contact_no"));
				contact.setIsVip(rs.getBoolean("contact_vip"));
				contact.setId(rs.getLong("contact_id"));
				contact.setCompany_id(rs.getLong("company_id"));
				
				clist.add(contact);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}	
		return clist;
	}
	
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "phonebook",
				"password");
		connection.setAutoCommit(false);
		return connection;
	}
}
