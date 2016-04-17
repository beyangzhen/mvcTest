package com.briup.common.test;

import com.briup.bean.Customer;
import com.briup.dao.CustomerDao;

public class DaoTest {
	public static void main(String[] args) {
		CustomerDao dao = new CustomerDao();
		dao.save(new Customer(null, "yz", "123321", 12));
		//dao.deleteById(1);
		//dao.update(new Customer(null, "杨祯", "123321", 20), 1L);
		//dao.findByName("杨祯");
		System.out.println("success!");
	}
}
