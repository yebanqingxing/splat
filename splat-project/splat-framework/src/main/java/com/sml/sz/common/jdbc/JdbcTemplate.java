package com.sml.sz.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * JDBC模板
 * @author yues
 *
 */
public class JdbcTemplate {
	
	/**
	 * 释放连接
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeCon(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("ResultSet Exception?");
			e.printStackTrace();
		}
		
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			System.out.println("Statement Exception?");
			e.printStackTrace();
		}
		
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Connection Exception?");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取数据库连接实例
	 * @return
	 */
	public static Connection getConnInstance(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/splat?user=root&password=123456&useServerPrepStmts=false&rewriteBatchedStatements=true&autocommit=false;");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/splat?user=root&password=123456");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show tables;");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
