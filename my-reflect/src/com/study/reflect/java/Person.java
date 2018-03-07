package com.study.reflect.java;

import java.io.Serializable;

public class Person extends Human implements Serializable{
	private int age;
	private String name;
	private String address;
	
	public Person() {
		super();
	}
//	public Person(int age, String name, String address){
//		this.age = age;
//		this.name = name;
//		this.address = address;
//	}
	public Person(int age, String name, String address) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
	}
	
	private String getPrivateInfo(String name, String address){
		return name + "/" + address ;
	}
}
