package com.briup.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICustomerService customerService;

	public CustomerServlet() {
		super();
		customerService = new CustomerServiceImpl(); // 向上转型对象，但调用的还是子类重写的方法
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("当前执行：" + action);
		if(null == action || "".equals(action)) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if("login".equals(action)) {
			login(request, response);
		} else if("register".equals(action)) {
			register(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 2. 获取参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// 3.调用service完成业务逻辑
		Customer customer = customerService.login(name, password);

		// 4.页面跳转
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 1.设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 2.获取参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));

		// 3.调用service层代码完成业务逻辑
		Customer customer = new Customer(null, name, password, age); // 获取的参数封装到bean中
		customerService.register(customer);
		// 4.页面跳转
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
