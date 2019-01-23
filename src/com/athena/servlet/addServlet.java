package com.athena.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.domain.Student;
import com.athena.service.StudentService;
import com.athena.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class addServlet
 */
public class addServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String sname = request.getParameter("sname");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String birthday = request.getParameter("birthday");
			String[] h = request.getParameterValues("hobby");
			String info = request.getParameter("info");
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			
			String hobby = Arrays.toString(h);
			hobby = hobby.substring(1, hobby.length()-1);
			
			Student student = new Student(sname,gender,phone,date,hobby,info);
			StudentService service = new StudentServiceImpl();
			service.insert(student);
			
			request.getRequestDispatcher("StudentListServlet").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
