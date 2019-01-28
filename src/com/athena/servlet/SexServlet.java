package com.athena.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.dao.StudentDao;
import com.athena.dao.impl.StudentDaoImpl;
import com.athena.domain.Student;
import com.athena.util.JsonDateValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class SexServlet
 */
public class SexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			String sgender = request.getParameter("sgender");
			StudentDao dao = new StudentDaoImpl();
			List<Student> list = dao.sexStudent(sgender);
			//3. 把list ---> json数据
			//JSONArray ---> 变成数组 ， 集合  []
			//JSONObject ---> 变成简单的数据  { name : zhangsan , age:18}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().write("系统故障");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
