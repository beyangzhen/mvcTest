package com.briup.bean;
/**
 * Pojo(JavaBean)
 * 顾客类是创建顾客对象的，顾客对象用来保存顾客信息的
 * */
public class Customer {
	private Long id; //包装类的类型(便于和其他对象进行运算)
	private String name;
	private String password;
	private Integer age; //包装类的类型(便于和其他对象进行运算)

	/**
	 * 构造函数
	 */
	public Customer() {

	}
	public Customer(Long id, String name, String password, Integer age) {
		this.name = name;
		this.password = password;
		this.age = age;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}

