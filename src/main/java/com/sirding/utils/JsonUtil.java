package com.sirding.utils;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;



/**
 * @description json解析工具类
 * @author xhf
 * @Date 2014-10-5
 */
public class JsonUtil {
	
	/**
	 * @description object对象转换为 json格式字符串
	 * @author xhf
	 * @Date 2014-10-5
	 * @param object 
	 * @return sw.toString()
	 * @throws IOException
	 */
	public static String object2Json(Object object) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, object);
		gen.close();
		return sw.toString();
	}
	
	/**
	 * @description json格式字符串转换为object对象
	 * @author xhf
	 * @Date 2014-10-5
	 * @param json
	 * @param cls
	 * @return mapper.readValue(json, cls)
	 * @throws IOException
	 */
	public static Object json2Object(String json, Class<?> cls) throws  IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, cls);
	}
	
	/**
	 * @description json格式字符串转换为list<T>对象
	 * @author liudh
	 * @Date 2015-12-23
	 * @param json
	 * @param instance 实例对象  
	 * @return List<T>
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2List(String json, T instance){
		JSONArray jsonArray = JSONArray.fromObject(json);  
		T[] items = (T[])JSONArray.toArray(jsonArray,instance.getClass());  
        List<T> listm = Arrays.asList(items);  
        return listm;
	}
}
