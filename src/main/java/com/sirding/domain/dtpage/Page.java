package com.sirding.domain.dtpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.sirding.domain.SimpleJson;

/**
 * @Described	: 解析jquery的datatables插件分页数据
 * @project		: com.sirding.domain.dtpage.Page
 * @author 		: zc.ding
 * @date 		: 2016年11月21日
 */
public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(getClass());
	
	//数据索引
	private int draw = 0;
	//起始页
	private int start = 0;
	//每页条数
	private int length = 10;
	//
	private String data;
	//进行排序的列
	private List<SimpleJson> sortList = new ArrayList<SimpleJson>();
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public List<SimpleJson> getSortList() {
		return sortList;
	}
	
	public void setSortList(List<SimpleJson> sortList) {
		this.sortList = sortList;
	}
	
	public String getData() {
		return data;
	}
	/**
	 * @Described	: 样例数据 [{"name":"sEcho","value":3},{"name":"iColumns","value":6},{"name":"sColumns","value":",,,,,"},{"name":"iDisplayStart","value":0},{"name":"iDisplayLength","value":10},{"name":"mDataProp_0","value":"id"},{"name":"sSearch_0","value":""},{"name":"bRegex_0","value":false},{"name":"bSearchable_0","value":true},{"name":"bSortable_0","value":true},{"name":"mDataProp_1","value":"loginName"},{"name":"sSearch_1","value":""},{"name":"bRegex_1","value":false},{"name":"bSearchable_1","value":true},{"name":"bSortable_1","value":true},{"name":"mDataProp_2","value":"function"},{"name":"sSearch_2","value":""},{"name":"bRegex_2","value":false},{"name":"bSearchable_2","value":true},{"name":"bSortable_2","value":false},{"name":"mDataProp_3","value":"function"},{"name":"sSearch_3","value":""},{"name":"bRegex_3","value":false},{"name":"bSearchable_3","value":true},{"name":"bSortable_3","value":true},{"name":"mDataProp_4","value":"note"},{"name":"sSearch_4","value":""},{"name":"bRegex_4","value":false},{"name":"bSearchable_4","value":true},{"name":"bSortable_4","value":true},{"name":"mDataProp_5","value":"function"},{"name":"sSearch_5","value":""},{"name":"bRegex_5","value":false},{"name":"bSearchable_5","value":true},{"name":"bSortable_5","value":false},{"name":"sSearch","value":""},{"name":"bRegex","value":false},{"name":"iSortCol_0","value":3},{"name":"sSortDir_0","value":"desc"},{"name":"iSortCol_1","value":1},{"name":"sSortDir_1","value":"desc"},{"name":"iSortingCols","value":2}]
	 * @author		: zc.ding
	 * @date 		: 2016年11月21日
	 * @return		: void
	 * @param data
	 */
	public void setData(String data) {
		initPage(data);
		this.data = data;
	}
	
	/**
	 * @Described	: 获得排序的信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月21日
	 * @return		: String
	 * @return
	 */
	public String getSort(){
		StringBuffer sb = new StringBuffer();
		for(SimpleJson obj : this.sortList){
			String sort = String.valueOf(obj.getValue());
			//列名称长度大于30并且既不是升序也不是降序，则不是正确的排序数据!!! 校验列名长度不是推荐的办法 !!!!!
			if(obj.getName().length() > 30 || !(sort.equalsIgnoreCase("DESC") || sort.equalsIgnoreCase("asc"))){
				continue;
			}
			sb.append(obj.getName() + " " + String.valueOf(obj.getValue())).append(",");
		}
		if(sb.toString().trim().endsWith(",")){
			return sb.toString().trim().substring(0, sb.toString().trim().length() - 1) + " ";
		}
		return sb.toString();
	}
	
	/**
	 * @Described	: 解析datatables参数，封装分页信息及检索条件信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月21日
	 * @return		: void
	 * @param data
	 */
	private void initPage(String data){
		Map<String, Object> map = this.getDataToMap(data);
		this.draw = (Integer)map.get("sEcho");
		this.start = (Integer)map.get("iDisplayStart");
		this.length = (Integer)map.get("iDisplayLength");
		//解析排序列接升降序
		initSortCols(map);
		
	}
	
	/**
	 * @Described	: 解析排序的列
	 * @author		: zc.ding
	 * @date 		: 2016年11月21日
	 * @return		: void
	 * @param map
	 */
	private void initSortCols(Map<String, Object> map){
		int colsNum = (Integer)map.get("iSortingCols");
		for(int i = 0; i < colsNum; i++){
			String key = "iSortCol_" + i;
			int index = (Integer)map.get(key);
			String colName = (String)map.get("mDataProp_" + index);
			Object sort = map.get("sSortDir_" + i);
			sortList.add(new SimpleJson(colName, sort));
		}
	}
	
	/**
	 * @Described	: 将list转为Map
	 * @author		: zc.ding
	 * @date 		: 2016年11月21日
	 * @return		: Map<String,Object>
	 * @param String
	 * @return
	 */
	private Map<String, Object> getDataToMap(String data){
		logger.debug(data);
		List<SimpleJson> list = JSONArray.parseArray(data, SimpleJson.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list != null){
			if(logger.isDebugEnabled()){
				for(SimpleJson dt : list){
					logger.debug("name:" + dt.getName() + "\t" + dt.getValue());
				}
			}
			for(SimpleJson obj : list){
				map.put(obj.getName(), obj.getValue());
			}
		}
		return map;
	}
	

}
