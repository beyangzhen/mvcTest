package com.briup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.briup.bean.Customer;
import com.briup.common.util.ConnectionFactory;
/**
 * 与数据库交互的类，不参与业务逻辑运算
 * save
 * find/query
 * update
 * delete
 * */
public class CustomerDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 */
	public CustomerDao() {

	}

	/**
	 * @param customer
	 */
	public void save(Customer customer) {
		//6大步骤
		try {
			try{
				//1.2 获取连接
				conn = ConnectionFactory.getConn();
				//3. 创建pstmt对象
				String sql = "insert into customer(name,password,age) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, customer.getName());
				pstmt.setString(2, customer.getPassword());
				pstmt.setInt(3, customer.getAge());
				//4. 执行sql
				pstmt.executeUpdate();
				
			} finally {
				//6释放资源
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 */
	public void deleteById(long id) {
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="delete from customer where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				pstmt.executeUpdate();
			}
			finally {
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param customer
	 */
	public void update(Customer customer, long id) {
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="update customer set name = ?,password = ?,age = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, customer.getName());
				pstmt.setString(2, customer.getPassword());
				pstmt.setInt(3, customer.getAge());
				pstmt.setLong(4, id);
				pstmt.executeUpdate();
			}
			finally {
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param name
	 * @return com.briup.ch08.bean.Customer
	 */
	public Customer findByName(String name) {
		Customer customer = new Customer();
		
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="select* from customer where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					customer.setId(rs.getLong("id"));
					customer.setName(rs.getString("name"));
					customer.setPassword(rs.getString("password"));
					customer.setAge(rs.getInt("age"));
				}
			}
			finally {
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
}
