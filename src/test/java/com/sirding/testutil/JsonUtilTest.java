package com.sirding.testutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sirding.core.utils.json.jsonlib.JsonUtil;
import com.sirding.core.utils.secure.DesUtil;
import com.sirding.core.utils.secure.RSAUtil;
import com.sirding.domain.MfqJson;

/**
 * JsonUtil单元测试
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class JsonUtilTest {
	
	static final String prik = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJqs5PWzTadY9WF4mXPksAZiReZCA1TE+2d04pXIx1RFUvxWhm2xWgpWxjf++JYJtwMYuYPreFARedoSAfJpiLvMpIYBuIslGcDdcthx+5ZTGQhyampi8ZMFSoYCQzjVfl9VWeHo4N217yl6bGupp1xYdOUuxLuuQY5qT4oEnTjzAgMBAAECgYAUdvGoLHwGuzdkGQ+8jLJZdeEnsO495f4ZVxavmKv+01lCd7Q7iS6PlaGOQRbkyw5Cw5+v+5Ski1ti5eUKDLqBYYMpEksguHY//E60KvGdRZEjPzBmRSBWq1aPTnoZwjv9mzTi6KR/cSux1t2sI5bs41mjpNs4gNPwbQAQ5trcgQJBAN1C7LJyQhHtAh1yTW2HeSXrjvE9AevtjpQnZ4pNWDpdmvrZEQ6b/7Rm3pK8L9+ick9+4QJK0jX8TjCwWb1o9bMCQQCy9bO4UGHT75acYE3Lr5kNdQQb1TybT9caY8lSN5cVAly/wUM9yAnbxH26cWgvX0WrHn5UAy/DqTXsUHOnT4/BAkAeYN/qqDFWELpnnJfw39bMVAkuKfgnklgvEzHuFSx15wAuTLtAzD5RYIZSky/nfoA/VeZC0E8IRnndyXiovs5tAkEAsLWvHdu5jbWxqX3qs6sT3VbjlLwtWUswIq3P2DBuLd+kR/CE/hdtrKVMZPKgps5Tk9HLehefbXvDdDJI0TfdwQJBALWAKnK83f6qVk/3eYTqLpmW6lIpJPphveh2DF74ULVT6nsz5UKwaXycCv69J4p+4RnMOo29ZwHJQ+o4IvQ57Hs=";
	static final String pubk = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCarOT1s02nWPVheJlz5LAGYkXmQgNUxPtndOKVyMdURVL8VoZtsVoKVsY3/viWCbcDGLmD63hQEXnaEgHyaYi7zKSGAbiLJRnA3XLYcfuWUxkIcmpqYvGTBUqGAkM41X5fVVnh6ODdte8pemxrqadcWHTlLsS7rkGOak+KBJ048wIDAQAB";
	
	Logger logger = Logger.getLogger(JsonUtilTest.class);
	@Test
	public void testObj2Json(){
		JsonPojo obj = getJsonPojo();
		String msg = JsonUtil.objectToJson(obj);
		logger.debug("object ==> Json");
		logger.debug(msg);
		logger.debug("==================");
		logger.debug("json ===> object");
		JsonPojo jobj = JsonUtil.jsonToObject(msg, JsonPojo.class);
		logger.debug("name:" + jobj.getName() + "\tpwd:" + jobj.getPwd());
		
	}
	
	@Test
	public void testCollection2Json(){
		List<JsonPojo> list = new ArrayList<JsonPojo>();
		list.add(getJsonPojo());
		list.add(getJsonPojo());
		list.add(getJsonPojo());
		String msg = JsonUtil.collectionToJson(list);
		logger.debug("List ==> Json");
		logger.debug(msg);
		logger.debug("==================");
		logger.debug("json ===> List");
		Collection<JsonPojo> c = JsonUtil.jsonToCollection(msg, JsonPojo.class);
		List<JsonPojo> l = (List<JsonPojo>)c;
		logger.debug(l);
		logger.debug(l.size());
	}
	
	@Test
	public void testMap2Json(){
		Map<String, JsonPojo> map = new HashMap<String, JsonPojo>();
		map.put("1", getJsonPojo());
		map.put("2", getJsonPojo());
		map.put("3", getJsonPojo());
		String data = JsonUtil.mapToJson(map);
		logger.debug("Map ==> Json");
		logger.debug(data);
		logger.debug("==================");
		logger.debug("json ===> Map");
		Map<String, JsonPojo> m = JsonUtil.jsonToMap(data, JsonPojo.class);
		if(m != null){
			for(String key : m.keySet()){
				Object o = m.get(key);
				JsonPojo jp = (JsonPojo)o;
				logger.debug("key :" + key + "\tname:" + jp.getName() + "\tobject:" + jp);
			}
		}
		
	}
	private JsonPojo getJsonPojo()	{
		JsonPojo obj = new JsonPojo();
		obj.setName("sirding_" + System.currentTimeMillis());
		obj.setPwd("sirding");
		return obj;
		
	}
	
	
	
	
	@Test
	public void test(){
		MfqJson obj = new MfqJson();
		//注册
		obj.setMobile("14510001112"); //14510001112 14510001113
		obj.setPassword("a12345");
		//实名
		obj.setUserName("张三");
		obj.setIdentity("123456201601012221");
		//绑卡
		obj.setBankCard("6217000010048002961");
//		obj.setBankCode("01050000");
//		obj.setBankName("中国建设银行");
		obj.setBankAddr("北京大兴");
		
		obj = this.getObj();
		try {
			String str = com.sirding.core.utils.json.jsonlib.JsonUtil.objectToJson(obj);
			System.out.println("======加密====");
			String encodeMsg = DesUtil.encrypt(str);
			System.out.println(encodeMsg);
			System.out.println("======解密====");
			String msg = DesUtil.decrypt(encodeMsg);
			System.out.println(msg);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRas(){
		MfqJson obj = this.getObj();
		try {
			String str = JsonUtil.objectToJson(obj);
			System.out.println(str);
			System.out.println("======加密====");
			String encodeMsg = RSAUtil.encrypt(str);
			System.out.println(encodeMsg);
			System.out.println("======解密====");
			String msg = RSAUtil.decrypt(encodeMsg);
			System.out.println(msg);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private MfqJson getObj(){
		MfqJson obj = new MfqJson();
		//注册
		obj.setMobile("14510001113"); //14510001112 14510001113
		obj.setPassword("a12345");
		//实名
		obj.setUserName("孙兆才");
		obj.setIdentity("370111195405051019");
//		obj.setUserCode("fd558f42-95bf-4297-9c7d-70a7e0c29dd3");
		obj.setUserCode("93e46b2b-1bd0-4934-9f47-3c91787706f0");
		//绑卡
		obj.setBankCard("6217000010048002961");
//		obj.setBankCode("01050000");
//		obj.setBankName("中国建设银行");
		obj.setBankAddr("北京大兴");
		obj.setProvince("11");//北京
		obj.setCity("15");//大兴
		return obj;
	}
	
	
	public static class JsonPojo{
		private String name;
		private String pwd;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}
	
	
}
