package com.duiyi.util;

public class StringUtil {
	private StringUtil() {
	}
	
	/**
	 * ����Ƿ�Ϊ���ַ���
	 *
	 * @param string �������ַ���
	 * @return true ���ַ�����false �ǿ��ַ���
	 */
	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}
}
