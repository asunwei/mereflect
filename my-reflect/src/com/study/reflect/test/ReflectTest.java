package com.study.reflect.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.study.reflect.java.Person;

/**
 * 
 * @author wei.sun02
 *
 */
public class ReflectTest {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException{
		/*
		 * 获取一个类的相关信息
		 */
		System.out.println("---------------------获取一个类的相关信息 line21---------------------");
		Person person = new Person();
		System.out.println("person类的名称： " + person.getClass().getName());
		
		/*
		 * 实例化Class类对象
		 */
		System.out.println("-----------------------实例化Class类对象-----------------");
		//方式一、二、三
		Class<?> class1 = Class.forName("com.study.reflect.java.Person");
		@SuppressWarnings("rawtypes")
		Class class2 = new Person().getClass();
		@SuppressWarnings("rawtypes")
		Class class3 = Person.class;
		System.out.println(class1);
		System.out.println(class2);
		System.out.println(class3);
		
		ClassLoader cl = class2.getClassLoader();
		System.out.println(cl);
		
		
		/*
		 * 获取一个对象的父类和实现的接口
		 */
		System.out.println("------------------获取一个对象的父类和实现的接口 line42--------------------");
		Class<?> clazz = Class.forName("com.study.reflect.java.Person");
		//获取父类
		Class<?> parentClass = clazz.getSuperclass();
		System.out.println("clazz的父类为: " + parentClass);
		
		//获取所有的接口
		Class<?> inter[]  = clazz.getInterfaces();
		for(Object o : inter){
			System.out.println("clazz的接口为： " + o);
		}
		
		/*
		 * 获取类的构造方法
		 */
		System.out.println("-------------------------获取类的构造方法 line57-----------------------");
		//第一种方法，实例化默认构造方法，调用set方法。
		Class<?> clazz1 = null;
		clazz1 = Class.forName("com.study.reflect.java.Person");
		Person person1 = (Person) clazz1.newInstance();
		person1.setAddress("上海");
		
		//第二种方法，取得所有的构造方法，使用构造函数赋值。
		Class<?> clazz2 = Class.forName("com.study.reflect.java.Person");
		Constructor<?> cons[] = clazz2.getConstructors();
		//查看每个方法的构造方法需要的参数。
		for(Constructor<?> o : cons ){
			System.out.println(o);
		}
		Person p0 = (Person) cons[0].newInstance();
		Person p1 = (Person) cons[1].newInstance(25,"AA","滁州");
		System.out.println(p0);
		System.out.println(p1);
		
		/*
		 * 获取类的全部属性
		 */
		System.out.println("-----------------------获取类的全部属性 line79-------------------");
		@SuppressWarnings("unused")
		Class<?> clazz3 = Class.forName("com.study.reflect.java.Person");
		Person person0 = (Person) clazz3.newInstance();
		Field fields[] = clazz.getDeclaredFields();
		for(Field f : fields){
			//权限修饰符
			int m =  f.getModifiers();
			String pri = Modifier.toString(m);
			//属性类型
			Class<?> type = f.getType();
			//属性名称
			String pn = f.getName();
			//修改属性的值
			if("name".equals(pn)) {
				f.setAccessible(true);
				f.set(person0, "sunwei");
			}
			System.out.println("访问权限 "+ pri+ "; " + "属性类型" + type + "; " + "属性名称" + pn);
			System.out.println(person0);
		}
		
		/*
		 * 获取类的全部方法
		 */
		System.out.println("-----------------------获取类的全部方法 line96-------------------");
		Class<?> clazz4 = Class.forName("com.study.reflect.java.Person");
		Method[] methods = clazz4.getMethods();
		for(Method m : methods){
			System.out.println(m);
		}
		
		/*
		 * 通过反射机制获取某个方法
		 */
		System.out.println("-----------------------通过反射机制获取某个方法 line107-------------------");
		Class<?> clazz5 = Class.forName("com.study.reflect.java.Person");
		Person p = (Person) clazz5.newInstance();
		//获得某个public方法
		Method m1 = clazz5.getMethod("setAge",int.class);
		//给该方法传值
		m1.invoke(p,25);
		Method m2 = clazz5.getMethod("getAge");
		Object value = m2.invoke(p);
		System.out.println(value);
		
		//获得private方法
		Method m3 = clazz5.getDeclaredMethod("getPrivateInfo",String.class,String.class);
		m3.setAccessible(true);
		Object value1 = m3.invoke(p,"CC","wuhu");
		System.out.println(value1);
		
		/*
		 * 通过反射获得某个属性
		 */
		System.out.println("------------------------通过反射获得某个属性 line129--------------------");
		Class<?> clazz6 = Class.forName("com.study.reflect.java.Person");
		Person p2 = (Person) clazz6.newInstance();
		Field f = clazz6.getDeclaredField("address");
		f.setAccessible(true);
		f.set(p2, "安徽");
		
		Method getA = clazz6.getDeclaredMethod("getAddress");
		System.out.println(getA.invoke(p2));
	}
}
