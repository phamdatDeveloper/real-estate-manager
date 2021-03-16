package com.laptrinhjavaweb.controller;

import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		String[] ids = {"1","2","3"};
		String id = Arrays.toString(ids);
		System.out.println(id);
		id.replace("\"", "");
		System.out.println(id);
	}
}
