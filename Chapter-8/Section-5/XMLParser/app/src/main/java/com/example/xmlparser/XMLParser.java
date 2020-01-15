package com.example.xmlparser;

import java.io.InputStream;
import java.util.List;

public interface XMLParser {
	/**
	 * 解析输入流 得到Product对象集合
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public List<Product> parse(InputStream is) throws Exception;
	
	/**
	 * 序列化Product对象集合 得到XML形式的字符串
	 * @param products
	 * @return
	 * @throws Exception
	 */
	public String serialize(List<Product> products) throws Exception;
}