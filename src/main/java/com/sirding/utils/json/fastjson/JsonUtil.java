package com.sirding.utils.json.fastjson;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * fast-json格式数据与对象转换工具
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class JsonUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * 
	 * @param clazz
	 * @param data
	 * @return
	 */

		private static SerializerFeature[] features = {SerializerFeature.WriteClassName, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect};
		@SuppressWarnings("deprecation")
		protected static SerializerFeature[] features2 = {
			SerializerFeature.QuoteFieldNames, SerializerFeature.UseSingleQuotes, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteEnumUsingToString,
			SerializerFeature.UseISO8601DateFormat, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.SkipTransientField, SerializerFeature.SortField, SerializerFeature.WriteClassName,
			SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteSlashAsSpecial, SerializerFeature.BrowserCompatible, SerializerFeature.WriteDateUseDateFormat,
			SerializerFeature.NotWriteRootClassName, SerializerFeature.DisableCheckSpecialChar, SerializerFeature.BeanToArray, SerializerFeature.WriteNonStringKeyAsString
		};

		/**
		 * 序列化对象
		 * @param obj
		 * @param map	设置include或是exclude中的值、包含或是剔除的属性字段
		 * @return
		 */
		public static String objectToJson(Object obj, Map<Class<?>, String[]> map){
			ComplexPropertyPreFilter cpp = new ComplexPropertyPreFilter();
			if(map != null && map.size() > 0){
				cpp.setExcludes(map);
			}
			logger.debug(JSON.toJSONString(obj, cpp, features));
			String data = JSON.toJSONString(obj, cpp, features);
			showMsg(data);
			return data;
		}
		
		/**
		 * 过滤对象中单个属性字段
		 * @param obj
		 * @param clazz
		 * @param arr
		 * @return
		 */
		public static String objectToJson(Object obj, Class<?> clazz, String... arr){
			SimplePropertyPreFilter spp = new SimplePropertyPreFilter(clazz, arr);
			String data = JSON.toJSONString(obj, spp, features);
			showMsg(data);
			return data;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 */
		public static Object jsonToObject(String data){
			return JSONObject.parse(data);
		}
		
		public static List<?> jsonToCollection(String data, Class<?> clazz){
			return JSONArray.parseArray(data, clazz);
		}
		
		public static String collectionToJson(List<?> list, Map<Class<?>, String[]> map){
			ComplexPropertyPreFilter cpp = new ComplexPropertyPreFilter();
			if(map != null && map.size() > 0){
				cpp.setExcludes(map);
			}
			return  JSONArray.toJSONString(list, cpp, features);
		}
		
		
		private static void showMsg(Object obj){
//			logger.debug("########################## alibaba fastJson ####################");
//			logger.debug(obj);
//			logger.debug("########################## alibaba fastJson ####################");
		}
		
		
		
	
		 /**
		 * demo
		 * @param obj
		 * @return
		 */
		/**
	public String objectToJsonByFastjson(Object obj){
		String data = "";
		//alibaba fastJson
		Map<Class<?>, String[]> map = new HashMap<Class<?>, String[]>();
		map.put(AgentUserAgent.class, new String[]{"agentUserInfo","agentInfo"});
		map.put(SpcsInfo.class, new String[]{"agentInfos"});
		data = JsonUtil.objectToJson(obj, map);
		return data;
	}
		**/
}
