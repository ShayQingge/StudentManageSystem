package com.athena.servlet.ajax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.dao.StudentDao;
import com.athena.dao.impl.StudentDaoImpl;
import com.athena.domain.Student;
import com.athena.service.StudentService;
import com.athena.service.impl.StudentServiceImpl;

/**
 * Servlet implementation cl 
 * ass StudentListServlet
 */
public class StudentListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
