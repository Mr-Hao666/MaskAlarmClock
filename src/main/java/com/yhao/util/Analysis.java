package com.yhao.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analysis {
	
	
	
	/**
	 * 给对象设值
	 * @param t
	 * @param filed
	 * @param value
	 * @return
	 */
	protected static <T> T setVelue(T t,String filed,String value) {
		
		Method[] methods=t.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false || methodName.length() <= 3) { // only  setter method
				continue;
			}
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1) { // only one parameter
				continue;
			}
			String attrName = firstCharToLowerCase(methodName.substring(3));
			String paraName = attrName;
			if (filed.equals(paraName)) {
				try {
					method.invoke(t, value);
				} catch (Exception e) {
			
				  throw new RuntimeException(e);
				}
			}
		}
		
		
		return t;
	}
	
	
	/**
	 * 实例化对象
	 * @param objClass
	 * @return
	 */
	protected static <T> T createInstance(Class<T> objClass) {
		try {
			return objClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 验证上传模板是否和对象吻合
	 * @param beanClass
	 * @param filedTemp
	 */
	protected static void validateFileds(Class beanClass,String filedTemp){
		
		List<String> temp=Arrays.asList(filedTemp.split("\\|"));
		validateFiledsForList( beanClass,temp );
		
	}
	
	/**
	 * 验证列名是否正确
	 * @param beanClass
	 * @param filedTemp
	 */
	protected static void validateFiledsForList(Class beanClass,List<String> filedTemp){
		
	
		List<String> fileds=new ArrayList<String>();
		Method[] methods = beanClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false || methodName.length() <= 3) {	// only setter method
				continue;
			}
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1) {						// only one parameter
				continue;
			}
			String attrName = firstCharToLowerCase(methodName.substring(3));
			fileds.add(attrName);
		}
		
		for (String filed : filedTemp) {
			if(!fileds.contains(filed.trim())){
				throw new RuntimeException("列名错误【"+filed+"】");
			}
		}
		
	}
	
	
	/**
	 * 字符串处理
	 * @param str
	 * @return
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

}

