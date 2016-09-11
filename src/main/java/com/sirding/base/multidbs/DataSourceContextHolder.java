package com.sirding.base.multidbs;


public class DataSourceContextHolder {
	public static final String DATA_SOURCE = "dataSource";

	public static final String DATA_SOURCE_1 = "dataSource_1";
	public static final String DATA_SOURCE_2 = "dataSource_2";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}

