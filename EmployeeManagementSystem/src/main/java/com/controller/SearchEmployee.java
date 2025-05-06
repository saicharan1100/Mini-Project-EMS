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

@WebServlet("/search")
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDAO dao = new EmployeeDAO();
		try {
			Employee employee = dao.getEmployee(id);
			if (employee.getId()==0) {
				request.setAttribute("status", "Employee not found..");
				RequestDispatcher dispatcher= request.getRequestDispatcher("search.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("employee", employee);
				RequestDispatcher dispatcher= request.getRequestDispatcher("viewemp.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
