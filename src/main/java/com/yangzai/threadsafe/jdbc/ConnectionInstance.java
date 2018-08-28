package com.yangzai.threadsafe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试 JDBC中的connection是否是线程安全的
 * @author 阳仔
 */
public class ConnectionInstance {
	private Connection conn;
	
	public void getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/ant?"
	                + "user=root&password=tiger&characterEncoding=UTF8&useSSL=true";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url);
	}
	public void execute(int demo_id){
		try {
			getConnection();
			PreparedStatement ps = conn.prepareStatement("select demo_desc from demo where demo_id=?");
			PreparedStatement ps2 = conn.prepareStatement("select demo_desc from demo where demo_id=2");
			ps.setInt(1, demo_id);
			// 第一个结果集
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String demo_desc  =  rs.getString("demo_desc");
				System.out.println(demo_id+"--"+demo_desc);
			}
			// 第二个结果集
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				String demo_desc  =  rs2.getString("demo_desc");
				System.out.println(2+"--"+demo_desc);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
