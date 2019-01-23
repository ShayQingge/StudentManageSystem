package com.athena.dao;

import java.sql.SQLException;
import java.util.List;

import com.athena.domain.Student;

public interface StudentDao {
	
	//接口中只能定义常量
	int PAGE_SIZE = 5; //一页显示多少条记录
	
	List<Student> findStudentByPage(int currentPage) throws SQLException;
	
	List<Student> findAll() throws SQLException;
	
	void insert(Student student) throws SQLException;
	
	void delete(int sid) throws SQLException;
	
	Student findStudentById(int sid) throws SQLException;

	void update(Student stu) throws SQLException;
	
	List<Student> searchStudent(String sname,String sgender) throws SQLException;
	
	/**
	 * 查询总的学生记录数
	 * @return
	 * @throws SQLException
	 */
	int findCount() throws SQLException ;
	
}
