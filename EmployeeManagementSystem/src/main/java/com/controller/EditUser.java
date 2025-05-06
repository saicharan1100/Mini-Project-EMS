package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmployeeDAO;
import com.model.Employee;

@WebServlet("/edituser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(request.getParameter("id")));

		employee.setName(request.getParameter("name"));
		employee.setEmail(request.getParameter("email"));
		employee.setPassword(request.getParameter("password"));
		employee.setDepartment(request.getParameter("department"));
		employee.setMobile(request.getParameter("mobile"));
		employee.setAddr(request.getParameter("addr"));
		employee.setSalary(Double.parseDouble(request.getParameter("salary")));


		EmployeeDAO dao = new EmployeeDAO();
		try {
			boolean status = dao.edit(employee);
			if (status) {
				dao.commit();
				request.setAttribute("status", "Updated Successfully........");
			} else {
				dao.rollback();
				request.setAttribute("status", "Updation Failed.......");

			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
