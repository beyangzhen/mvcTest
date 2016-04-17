package com.briup.common.test;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

public class ServiceTest {
	public static void main(String[] args) {
		ICustomerService customerService = new CustomerServiceImpl();
		customerService.register(new Customer(null, "李四", "123321", 12));
		//System.out.println(customerService.login("杨祯", "123321"));
		
	}
}
