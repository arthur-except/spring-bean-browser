package com.arthur.bean.utils;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author arthur
 *
 */
public class JsonResultMapUtils {
	
	public enum Code{
		SUCCESS(200, "操作成功！！！"), ERROR(500, "对不起，操作出错！！！");
		
		private final Integer code;
		
		private final String message;
		
		private Code(int code, String message){
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
		
	}
	
	public static Map<String, Object> getCodeAndMesMap(Code code, Object obj){
		return getObjectResultMap(code.getCode(), code.getMessage(), obj);
	}
	
	public static Map<String, Object> getObjectResultMap(Integer code, String message, Object obj){
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("code", code);
		resultMap.put("message", message);
		resultMap.put("result", obj);
		
		return resultMap;
	}
	
}
