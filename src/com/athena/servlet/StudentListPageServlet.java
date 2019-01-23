package com.athena.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.domain.PageBean;
import com.athena.service.StudentService;
import com.athena.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class StudentListPageServlet
 */
public class StudentListPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 获取需要显示的页码数
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("currentPage = " + currentPage );
			//2. 根据指定的页数，去获取该页的数据回来
			StudentService service = new StudentServiceImpl();
			PageBean pageBean = service.findStudentByPage(currentPage);
			System.out.println(pageBean);
			request.setAttribute("pageBean", pageBean);
			
			//3. 跳转界面。
			request.getRequestDispatcher("list_page.jsp").forward(request, response);
			
		} catch (NumberFormatException e) {
			System.out.println(request.getParameter("currentPage"));
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
