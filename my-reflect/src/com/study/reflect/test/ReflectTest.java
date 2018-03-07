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
		 * ��ȡһ����������Ϣ
		 */
		System.out.println("---------------------��ȡһ����������Ϣ line21---------------------");
		Person person = new Person();
		System.out.println("person������ƣ� " + person.getClass().getName());
		
		/*
		 * ʵ����Class�����
		 */
		System.out.println("-----------------------ʵ����Class�����-----------------");
		//��ʽһ��������
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
		 * ��ȡһ������ĸ����ʵ�ֵĽӿ�
		 */
		System.out.println("------------------��ȡһ������ĸ����ʵ�ֵĽӿ� line42--------------------");
		Class<?> clazz = Class.forName("com.study.reflect.java.Person");
		//��ȡ����
		Class<?> parentClass = clazz.getSuperclass();
		System.out.println("clazz�ĸ���Ϊ: " + parentClass);
		
		//��ȡ���еĽӿ�
		Class<?> inter[]  = clazz.getInterfaces();
		for(Object o : inter){
			System.out.println("clazz�Ľӿ�Ϊ�� " + o);
		}
		
		/*
		 * ��ȡ��Ĺ��췽��
		 */
		System.out.println("-------------------------��ȡ��Ĺ��췽�� line57-----------------------");
		//��һ�ַ�����ʵ����Ĭ�Ϲ��췽��������set������
		Class<?> clazz1 = null;
		clazz1 = Class.forName("com.study.reflect.java.Person");
		Person person1 = (Person) clazz1.newInstance();
		person1.setAddress("�Ϻ�");
		
		//�ڶ��ַ�����ȡ�����еĹ��췽����ʹ�ù��캯����ֵ��
		Class<?> clazz2 = Class.forName("com.study.reflect.java.Person");
		Constructor<?> cons[] = clazz2.getConstructors();
		//�鿴ÿ�������Ĺ��췽����Ҫ�Ĳ�����
		for(Constructor<?> o : cons ){
			System.out.println(o);
		}
		Person p0 = (Person) cons[0].newInstance();
		Person p1 = (Person) cons[1].newInstance(25,"AA","����");
		System.out.println(p0);
		System.out.println(p1);
		
		/*
		 * ��ȡ���ȫ������
		 */
		System.out.println("-----------------------��ȡ���ȫ������ line79-------------------");
		@SuppressWarnings("unused")
		Class<?> clazz3 = Class.forName("com.study.reflect.java.Person");
		Person person0 = (Person) clazz3.newInstance();
		Field fields[] = clazz.getDeclaredFields();
		for(Field f : fields){
			//Ȩ�����η�
			int m =  f.getModifiers();
			String pri = Modifier.toString(m);
			//��������
			Class<?> type = f.getType();
			//��������
			String pn = f.getName();
			//�޸����Ե�ֵ
			if("name".equals(pn)) {
				f.setAccessible(true);
				f.set(person0, "sunwei");
			}
			System.out.println("����Ȩ�� "+ pri+ "; " + "��������" + type + "; " + "��������" + pn);
			System.out.println(person0);
		}
		
		/*
		 * ��ȡ���ȫ������
		 */
		System.out.println("-----------------------��ȡ���ȫ������ line96-------------------");
		Class<?> clazz4 = Class.forName("com.study.reflect.java.Person");
		Method[] methods = clazz4.getMethods();
		for(Method m : methods){
			System.out.println(m);
		}
		
		/*
		 * ͨ��������ƻ�ȡĳ������
		 */
		System.out.println("-----------------------ͨ��������ƻ�ȡĳ������ line107-------------------");
		Class<?> clazz5 = Class.forName("com.study.reflect.java.Person");
		Person p = (Person) clazz5.newInstance();
		//���ĳ��public����
		Method m1 = clazz5.getMethod("setAge",int.class);
		//���÷�����ֵ
		m1.invoke(p,25);
		Method m2 = clazz5.getMethod("getAge");
		Object value = m2.invoke(p);
		System.out.println(value);
		
		//���private����
		Method m3 = clazz5.getDeclaredMethod("getPrivateInfo",String.class,String.class);
		m3.setAccessible(true);
		Object value1 = m3.invoke(p,"CC","wuhu");
		System.out.println(value1);
		
		/*
		 * ͨ��������ĳ������
		 */
		System.out.println("------------------------ͨ��������ĳ������ line129--------------------");
		Class<?> clazz6 = Class.forName("com.study.reflect.java.Person");
		Person p2 = (Person) clazz6.newInstance();
		Field f = clazz6.getDeclaredField("address");
		f.setAccessible(true);
		f.set(p2, "����");
		
		Method getA = clazz6.getDeclaredMethod("getAddress");
		System.out.println(getA.invoke(p2));
	}
}
