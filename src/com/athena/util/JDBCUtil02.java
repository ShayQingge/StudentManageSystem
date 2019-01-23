package com.athena.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**释放内存
 * @author Athena
 *
 */
public class JDBCUtil02 {
	
	static ComboPooledDataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	/**创建连接对象
	 * @author Athena
	 * @throws SQLException 
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**创建连接对象
	 * @author Athena
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs) {
		closeRs(rs);
		closeSt(st);
		closeConn(conn);
	}
	
	public static void release(Connection conn,Statement st) {
		closeSt(st);
		closeConn(conn);
	}
	
	public static void closeRs(ResultSet rs) {
		try {
			if( rs!=null ) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs = null;
		}
	}
	
	public static void closeSt(Statement st) {
		try {
			if( st!=null ) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st = null;
		}
	}
	
	public static void closeConn(Connection conn) {
		try {
			if( conn!=null ) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn = null;
		}
	}
}
