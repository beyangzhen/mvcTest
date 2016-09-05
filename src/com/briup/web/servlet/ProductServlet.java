package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Product;
import com.briup.service.IProductService;
import com.briup.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IProductService productService; 

	public ProductServlet() {
		super();
		productService = new ProductServiceImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("当前执行：" + action);
		if(null == action || "".equals(action)) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if("add".equals(action)) {
			add(request, response);
		} else if("remove".equals(action)) {
			remove(request, response);
		} else if("modify".equals(action)) {
			modify(request, response);
		} else if("query".equals(action)) {
			query(request, response);
		}

	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 2. 获取参数
		//String name = request.getParameter("name");
		//double price = Double.parseDouble(request.getParameter("price"));
		//int amount = Integer.parseInt(request.getParameter("amount"));
		Product product = new Product();
		try {
			// javabean 内省
			BeanInfo beanInfo = Introspector.getBeanInfo(product.getClass());
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : descriptors) {
				Method method = descriptor.getWriteMethod(); // setter方法
				if (method != null) {
					Class<?>[] types = method.getParameterTypes();
					if (null != types && types[0].equals(String.class)) { // 参数时String类型
						method.invoke(customer, request.getParameter(descriptor.getName()));
					}
					if (null != types && types[0].equals(Double.class)) { // 参数时Double类型
						method.invoke(customer, Double.parseDouble(request.getParameter(descriptor.getName())));
					}
					if (null != types && types[0].equals(Integer.class)) { // 参数时Integer类型
						method.invoke(customer, Integer.parseInt(request.getParameter(descriptor.getName())));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3.调用service完成业务逻辑
		Product dbProduct = productService.add(product);

		// 4.页面跳转
		request.setAttribute("product", dbProduct);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 2. 获取参数
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");

		// 3.调用service完成业务逻辑
		productService.remove(id);

		// 4.页面跳转
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 2. 获取参数
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		// 3.调用service完成业务逻辑
		Product product = new Product(null, name, price, amount);
		Product dbProduct = productService.modify(product, id);

		// 4.页面跳转
		request.setAttribute("product", dbProduct);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 2. 获取参数
		String name = request.getParameter("name");

		// 3.调用service完成业务逻辑
		Product product = productService.query(name);

		// 4.页面跳转
		request.setAttribute("product", product);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}
}

	

	

	
