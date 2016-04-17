package com.briup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.briup.bean.Product;
import com.briup.common.util.ConnectionFactory;

public class ProductDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ProductDao() {
		super();
	}
	
	public void save(Product product) {
		//6大步骤
		try {
			try{
				//1.2 获取连接
				conn = ConnectionFactory.getConn();
				//3. 创建pstmt对象
				String sql = "insert into product(name,price,amount) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, product.getName());
				pstmt.setDouble(2, product.getPrice());
				pstmt.setInt(3, product.getAmount());
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

	public void deleteById(long id) {
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="delete from product where id = ?";
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
	
	public void update(Product product, long id) {
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="update product set name = ?,price = ?,amount = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, product.getName());
				pstmt.setDouble(2, product.getPrice());
				pstmt.setInt(3, product.getAmount());
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

	public Product findByName(String name) {
		Product product = new Product();
		
		try {
			try {
				conn = ConnectionFactory.getConn();
				String sql ="select* from product where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					product.setId(rs.getLong("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setAmount(rs.getInt("amount"));
				}
			}
			finally {
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
}
