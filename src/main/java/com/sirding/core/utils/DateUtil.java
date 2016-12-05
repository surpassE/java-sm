package com.sirding.core.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Described	: 时间工具类
 * @project		: com.sirding.core.utils.DateUtil
 * @author 		: zc.ding
 * @date 		: 2016年12月5日
 */
public class DateUtil {
	public static final String WEEK[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	public static final String NUM_1[] = {"零","壹","贰","叁","肆","伍","录","柒","捌","玖"};
	public static final String NUM_2[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static String DATE_PATTERN_KEY = "yyyyMMddHHmmssSSS";
	/**
	 * yyyy-mm-dd
	 */
	public static String DATE_PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * HH:mm:ss
	 */
	public static String TIME_PATTERN = "HH:mm:ss";
	/**
	 * HH:mm
	 */
	public static String TIME_PATTERN_HH_MM = "HH:mm";
	public static String TIME_PATTERN_HOUR = "HH";
	public static String TIME_PATTERN_MINUTE = "mm";
	public static String TIME_PATTERN_SECOND = "ss";

	/**
	 * @Described			: 初始化时间格式化工具
	 * @author				: zc.ding
	 * @date 				: 2016年12月5日
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSimpleDateFormat(String... pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		if(pattern != null && pattern.length > 0){
			sdf = new SimpleDateFormat(pattern[0]);
		}
		return sdf;
	}
	
	/**
	 * @Described			: 获得当前时间格式化后的字符串
	 * @author				: zc.ding
	 * @date 				: 2016年12月5日
	 * @param pattern
	 * @return
	 */
	public static final String getDate(String... pattern) {
		return getSimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * @Described			: 获得指定date的时间字符串
	 * @author				: zc.ding
	 * @date 				: 2016年12月5日
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String getDate(Date date, String... pattern) {
		return getSimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * @Described			: 将指定的时间字符串转为日期类型的数据
	 * @author				: zc.ding
	 * @date 				: 2016年12月5日
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final Date getDate(String date, String... pattern){
		try {
			return getSimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Described			: 将当前时间转为如20161205121212100格式的字符串<br/>
	 * 							可由getDate完全取代
	 * @author				: zc.ding
	 * @date 				: 2016年12月5日
	 * @param pattern
	 * @return
	 */
	public static String getKeyDate(String... pattern){
		Date date = new Date();
		if(pattern != null && pattern.length > 0){
			return getSimpleDateFormat(pattern).format(date);
		}
		return getSimpleDateFormat(DATE_PATTERN_KEY).format(date);
	}
}