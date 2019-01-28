package com.athena.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.athena.dao.StudentDao;
import com.athena.domain.Student;
import com.athena.util.JDBCUtil02;
import com.athena.util.TextUtils;

public class StudentDaoImpl implements StudentDao{

	@Override
	public List<Student> findAll() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		return runner.query("select * from stu", new BeanListHandler<Student>(Student.class));
	}

	@Override
	public void insert(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		runner.update("insert into stu values(null,?,?,?,?,?,?)", 
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo()
				);
	}

	@Override
	public void delete(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		runner.update("delete from stu where sid = ?", sid);
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		return runner.query("select * from stu where sid = ?", new BeanHandler<Student>(Student.class), sid);
	}

	@Override
	public void update(Student stu) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		int update = runner.update("update stu set sname=?,gender=?,phone=?,birthday=?,hobby=?,info=? where sid = ?", 
				stu.getSname(),
				stu.getGender(),
				stu.getPhone(),
				stu.getBirthday(),
				stu.getHobby(),
				stu.getInfo(),
				stu.getSid());
		System.out.println(update);
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		System.out.println(sname + sgender);
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		String sql = "select * from stu where 1=1 ";
		List<String> list = new ArrayList<String>();
		
		if(!TextUtils.isEmpty(sname)) {
			sql = sql + " and sname like ?";
			list.add("%" + sname + "%");
		}
		if( !TextUtils.isEmpty(sgender) ) {
			sql = sql + " and gender = ?";
			list.add(sgender);
		}

		return 	runner.query(sql, new BeanListHandler<Student>(Student.class), list.toArray());

	}

	@Override
	public List<Student> findStudentByPage(int currentPage) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		//第一个问号，代表一页返回多少条记录  ， 第二个问号， 跳过前面的多少条记录。
		//5 0 --- 第一页 (1-1)*5
		//5 5  --- 第二页 (2-1)*5
		//5 10  --- 第三页
		return runner.query("select * from stu limit ? offset ?", new BeanListHandler<Student>(Student.class), PAGE_SIZE,(currentPage - 1)*PAGE_SIZE);
	}

	@Override
	public int findCount() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		//用于处理 平均值 、 总的个数。 
		Long  result = (Long) runner.query("select count(*) from stu" , new ScalarHandler() );

		return result.intValue();
	}

	@Override
	public int findCount(String sname, String sgender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		String sql = "select count(*) from stu where 1=1";
		List<Object> list = new ArrayList<>();
		if(!TextUtils.isEmpty(sname)) {
			sql = sql + " and sname like ?";
			list.add("%" + sname + "%");
		}
		if( !TextUtils.isEmpty(sgender) ) {
			sql = sql + " and gender = ?";
			list.add(sgender);
		}
		//用于处理 平均值 、 总的个数。 
		Long  result = (Long) runner.query(sql , new ScalarHandler() ,list.toArray());

		return result.intValue();
	}
	
	@Override
	public List<Student> sexStudent(String sgender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		String sql = "select * from stu where gender=? ";
		List<Student> list = runner.query(sql, new BeanListHandler<Student>(Student.class), sgender);
		return list;
	}

	@Override
	public List<Student> findStudentByPage(int currentPage, String sname, String sgender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		//第一个问号，代表一页返回多少条记录  ， 第二个问号， 跳过前面的多少条记录。
		//5 0 --- 第一页 (1-1)*5
		//5 5  --- 第二页 (2-1)*5
		//5 10  --- 第三页
		String sql = "select * from stu where 1=1";
		List<Object> list = new ArrayList<>();
		if(!TextUtils.isEmpty(sname)) {
			sql = sql + " and sname like ?";
			list.add("%" + sname + "%");
		}
		if( !TextUtils.isEmpty(sgender) ) {
			sql = sql + " and gender = ?";
			list.add(sgender);
		}
		sql += " limit ? offset ?";
		list.add(PAGE_SIZE);
		list.add(((currentPage - 1)*PAGE_SIZE));
		return runner.query(sql, new BeanListHandler<Student>(Student.class),list.toArray());
	
	}

	

}
