package com.alibaba.csp.sentinel.dashboard.domain;

public class ServiceProperty {

	static String index = "";
	static String code = "";
	static String name = "";
	/**
	 * 系统的编号 一般以j开头，后面数字
	 * @return
	 */
	public static String getIndex() {
		return index;
	}

	/**
	 * 系统代码 每个系统唯一
	 * @return
	 */
	public static String getCode() {
		return code;
	}

	/**
	 * 系统中文名称
	 * @return
	 */
	public static String getName() {
		return name;
	}
}
