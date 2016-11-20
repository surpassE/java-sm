package com.sirding.testdapage;

import java.io.FileInputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.sirding.domain.SimpleJson;
import com.sirding.domain.dtpage.Page;


public class TestDtPage {

//	[{"name":"sEcho","value":1},{"name":"iColumns","value":6},{"name":"sColumns","value":",,,,,"},{"name":"iDisplayStart","value":0},{"name":"iDisplayLength","value":10},{"name":"mDataProp_0","value":"id"},{"name":"sSearch_0","value":""},{"name":"bRegex_0","value":false},{"name":"bSearchable_0","value":true},{"name":"bSortable_0","value":true},{"name":"mDataProp_1","value":"loginName"},{"name":"sSearch_1","value":""},{"name":"bRegex_1","value":false},{"name":"bSearchable_1","value":true},{"name":"bSortable_1","value":true},{"name":"mDataProp_2","value":"function"},{"name":"sSearch_2","value":""},{"name":"bRegex_2","value":false},{"name":"bSearchable_2","value":true},{"name":"bSortable_2","value":false},{"name":"mDataProp_3","value":"function"},{"name":"sSearch_3","value":""},{"name":"bRegex_3","value":false},{"name":"bSearchable_3","value":true},{"name":"bSortable_3","value":true},{"name":"mDataProp_4","value":"note"},{"name":"sSearch_4","value":""},{"name":"bRegex_4","value":false},{"name":"bSearchable_4","value":true},{"name":"bSortable_4","value":true},{"name":"mDataProp_5","value":"function"},{"name":"sSearch_5","value":""},{"name":"bRegex_5","value":false},{"name":"bSearchable_5","value":true},{"name":"bSortable_5","value":false},{"name":"sSearch","value":""},{"name":"bRegex","value":false},{"name":"iSortCol_0","value":0},{"name":"sSortDir_0","value":"asc"},{"name":"iSortingCols","value":1}]
	
	Logger logger = Logger.getLogger(getClass());
	@Test
	public void test(){
		String msg = this.getData();
//		String msg = "[{\"name\":\"sEcho\",\"value\":1},{\"name\":\"iColumns\",\"value\":6}]";
		List<SimpleJson> list = JSONArray.parseArray(msg, SimpleJson.class);
		if(list != null){
			for(SimpleJson dt : list){
				logger.debug("name:" + dt.getName() + "\t" + dt.getValue());
			}
		}
	}
	
	private String getData(){
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream("G:/test/jsondata.txt");
			byte[] buf = new byte[1024*1024*100];
			int length = 0;
			while((length = fis.read(buf)) > 0){
				sb.append(new String(buf, 0, length));
			}
			fis.close();
			logger.debug(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	@Test
	public void testPage(){
		String data = this.getData();
		Page page = new Page();
		page.setData(data);
		logger.debug("===============================");
		logger.debug("start:" + page.getStart());
		logger.debug("length:" + page.getLength());
		logger.debug("sort info:" + page.getSort());
	}
	
}
