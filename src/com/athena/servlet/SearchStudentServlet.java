package com.athena.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.domain.Student;
import com.athena.service.StudentService;
import com.athena.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class SearchStudentServlet
 */
public class SearchStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String sname = request.getParameter("sname");
			String sgender = request.getParameter("sgender");
			
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.searchStudent(sname, sgender);
			
			System.out.println("list的大小是："+list.size());
			for (Student student : list) {
				System.out.println("stu="+student);
			}
			
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
