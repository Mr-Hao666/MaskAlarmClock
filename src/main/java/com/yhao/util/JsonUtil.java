package com.yhao.util;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by liuth on 2018/5/10.
 */
public class JsonUtil {

	/**
	 * json 转 List<T>
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {

		try {
			@SuppressWarnings("unchecked")
			List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
			return ts;
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}





}
