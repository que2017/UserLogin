package com.duiyi.util;

public class StringUtil {
	private StringUtil() {
	}
	
	/**
	 * ¼ì²éÊÇ·ñÎª¿Õ×Ö·û´®
	 *
	 * @param string ±»¼ì²éµÄ×Ö·û´®
	 * @return true ¿Õ×Ö·û´®£¬false ·Ç¿Õ×Ö·û´®
	 */
	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}
}
