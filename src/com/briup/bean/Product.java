package com.briup.bean;

public class Product {
	private Long id;
	private String name;
	private double price;
	private int amount;
	
	public Product() {
		super();
	}
	public Product(Long id, String name, double price, int amount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
