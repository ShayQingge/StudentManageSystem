package com.athena.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.athena.dao.StudentDao;
import com.athena.dao.impl.StudentDaoImpl;
import com.athena.domain.PageBean;
import com.athena.domain.Student;
import com.athena.service.StudentService;
import com.athena.util.JDBCUtil02;

public class StudentServiceImpl implements StudentService {
	
	public List<Student> findAll() throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.findAll();
	}
	
	public void insert(Student student) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.insert(student);
	}
	
	public void delete(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.delete(sid);
	}
	
	public Student findStudentById(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.findStudentById(sid);
	}
	
	public void update(Student stu) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.update(stu);
	}

	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.searchStudent(sname, sgender);
	}

	public PageBean findStudentByPage(int currentPage) throws SQLException {
		
		//封装分页的该页数据
		PageBean<Student> pageBean = new PageBean<Student>();
		
		int pageSize = StudentDao.PAGE_SIZE;
		
		pageBean.setCurrentPage(currentPage);//设置当前页
		pageBean.setPageSize(pageSize); //设置每页显示多少记录
		
		StudentDao dao = new StudentDaoImpl();
		List<Student> list = dao.findStudentByPage(currentPage);
		pageBean.setList(list);//设置这一页的学生数据
		
		//总的记录数， 总的页数。
		int count = dao.findCount();//设置总的记录数
		pageBean.setTotalSize(count);
		//200 ， 10 ==20   201 ， 10 = 21   201 % 10 == 0 ?201 / 10 :201 % 10 + 1
		pageBean.setTotalPage(count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1);
		return pageBean;
	}

	
}
