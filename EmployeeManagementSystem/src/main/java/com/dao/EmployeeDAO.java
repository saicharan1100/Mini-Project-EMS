package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Employee;

public class EmployeeDAO {
	Connection connection = null;

	public boolean save(Employee employee) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();
		String query = "insert into employee(name,email,password,department,mobile,addr,salary) values(?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, employee.getName());
		statement.setString(2, employee.getEmail());
		statement.setString(3, employee.getPassword());
		statement.setString(4, employee.getDepartment());
		statement.setString(5, employee.getMobile());
		statement.setString(6, employee.getAddr());
		statement.setDouble(7, employee.getSalary());
		int count = statement.executeUpdate();
		if (count == 1) {
			return true;
		}
		return false;
	}

	public void commit() throws SQLException {
		connection.commit();
		connection.close();
	}

	public void rollback() throws SQLException {
		connection.rollback();
		connection.close();
	}

	public boolean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {

		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);

		String query = "select count(*) from employee where email=? and password=?";

		PreparedStatement statement = connection.prepareStatement(query);

		statement.setString(1, email);

		statement.setString(2, password);

		ResultSet set = statement.executeQuery();

		int count = 0;

		if (set.next()) {
			count = set.getInt(1);
		}
		if (count == 1) {
			return true;
		}

		return false;
	}

	public List<Employee> findAll() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);
		String query = "select * from employee";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Employee> empList = new ArrayList<Employee>();
		while (set.next()) {
			Employee employee = new Employee();
			employee.setId(set.getInt(1));
			employee.setName(set.getString(2));
			employee.setEmail(set.getString(3));
			employee.setDepartment(set.getString(5));
			employee.setMobile(set.getString(6));
			employee.setAddr(set.getString(7));
			employee.setSalary(set.getDouble(8));

			empList.add(employee);

		}
		return empList;
	}

	public boolean deleteByID(int id) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();

		String query = "delete from employee where id=?";

		PreparedStatement statement = connection.prepareStatement(query);

		statement.setInt(1, id);

		int count = statement.executeUpdate();
		if (count == 1) {
			return true;
		}

		return false;
	}

	public Employee getEmployee(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);
		String query = "select * from employee where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		Employee employee = new Employee();
		while (set.next()) {
			employee.setId(set.getInt(1));
			employee.setName(set.getString(2));
			employee.setEmail(set.getString(3));
			employee.setDepartment(set.getString(5));
			employee.setMobile(set.getString(6));
			employee.setAddr(set.getString(7));
			employee.setSalary(set.getDouble(8));
		}
		return employee;
	}

	public boolean edit(Employee employee) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();
		String query = "update employee set id=?,name=?,email=?,department=?,mobile=?,addr=?,salary=? where id=?";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, employee.getId());

		statement.setString(2, employee.getName());
		statement.setString(3, employee.getEmail());
		statement.setString(4, employee.getDepartment());
		statement.setString(5, employee.getMobile());
		statement.setString(6, employee.getAddr());
		statement.setDouble(7, employee.getSalary());
		statement.setInt(8, employee.getId());

		int count = statement.executeUpdate();
		if (count == 1) {
			return true;
		}
		return false;
		
	
	}

	public Employee getEmployee(String email) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);
		String query = "select * from employee where email=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		ResultSet set = statement.executeQuery();
		Employee employee = new Employee();
		while (set.next()) {
			employee.setId(set.getInt(1));
			employee.setName(set.getString(2));
			employee.setEmail(set.getString(3));
			employee.setDepartment(set.getString(5));
			employee.setMobile(set.getString(6));
			employee.setAddr(set.getString(7));
			employee.setSalary(set.getDouble(8));
		}
		return employee;
		
		
	}

}
