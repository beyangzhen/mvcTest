package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.common.exception.ServiceException;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService {
	public CustomerDao customerDao;

	/**
	 */
	public CustomerServiceImpl() {
		customerDao = new CustomerDao();
	}

	/**
	 * @param name
	 * @param password
	 * @return com.briup.ch08.bean.Customer
	 */
	public Customer login(String name, String password) throws ServiceException {
		Customer customer = null;
		Customer dbCustomer = customerDao.findByName(name);
		
		if(null != dbCustomer) {
			if(dbCustomer.getPassword().equals(password)) {
				customer = dbCustomer;
			} else {
				throw new ServiceException("密码错误"); //利用throw抛出异常，然后上一层不继续抛(异常没得到处理)，来终止程序执行
			}
		} else {
			throw new ServiceException("该用户不存在"); //利用throw抛出异常，然后上一层不继续抛(异常没得到处理)，来终止程序执行
		}

		return customer;
	}

	/**
	 * @param customer
	 */
	public void register(Customer customer) {
		customerDao.save(customer);
	}
}
