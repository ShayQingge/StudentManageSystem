package com.athena.servlet.ajax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.domain.PageBean;
import com.athena.service.StudentService;
import com.athena.service.impl.StudentServiceImpl;
import com.athena.util.JsonDateValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class StudentListPageServlet
 */
public class StudentListPageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822067506626688085L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try {
			//1. 获取需要显示的页码数
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			//name  sex  的筛选
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			//2. 根据指定的页数，去获取该页的数据回来
			StudentService service = new StudentServiceImpl();
			PageBean pageBean = service.findStudentByPage(currentPage,name,sex);
			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
