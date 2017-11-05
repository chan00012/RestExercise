package com.lotus.phonebook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lotus.phonebook.beans.Company;

public class CompanyOJDBC implements CompanyDAO{
	
	public CompanyOJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to establish database connection.");
		}
	}
	
	
	@Override
	public Company retrieveCompanyByCode(String code) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Company company = new Company();
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM company WHERE company_code = ?");
			statement.setString(1, code);
			rs = statement.executeQuery();
			while(rs.next()) {
				company.setName(rs.getString("company_name"));
				company.setCode(rs.getString("company_code"));
				company.setDescription(rs.getString("company_description"));
				company.setId(rs.getLong("company_id"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return company;
		
	}
	
	public Company retrieveCompanyById(long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Company company = new Company();
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM company WHERE company_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while(rs.next()) {
				company = new Company();
				company.setName(rs.getString("company_name"));
				company.setCode(rs.getString("company_code"));
				company.setDescription(rs.getString("company_description"));
				company.setId(rs.getLong("company_id"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return company;
		
	}

	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
