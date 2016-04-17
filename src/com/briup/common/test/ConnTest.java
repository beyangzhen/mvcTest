package com.briup.common.test;

import com.briup.common.util.ConnectionFactory;

public class ConnTest {
	public static void main(String[] args) {
		try {
			System.out.println(ConnectionFactory.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
