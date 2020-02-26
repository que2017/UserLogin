package com.duiyi.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlDaoUtil {
	private static final String XML_PATH = XmlDaoUtil.class.getClassLoader().getResource("users.xml").getPath();
	
	private static Document dom = null;
	
	static {
		SAXReader reader = new SAXReader();
		try {
			dom = reader.read(XML_PATH);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取xml的dom
	 *
	 * @return dom
	 */
	public static Document getDom() {
		return dom;
	}
	
	/**
	 * 刷新xml文件
	 */
	public static void refreshXml() {
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(XML_PATH), OutputFormat.createPrettyPrint());
			writer.write(dom);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private XmlDaoUtil() {
	}
}
