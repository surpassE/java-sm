package com.sirding.testdemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class Demo1 {

	@Test
	public void test1(){
		Properties pros = System.getProperties();
		for(Object key : pros.keySet()){
			System.out.println((String)key + "\t:\t" + pros.getProperty((String)key));
		}
	}
	
	/**
	 * 
	 * @Described			: 测试加载jar中的properties文件
	 * @author				: zc.ding
	 * @date 				: 2017年2月8日
	 */
	@Test
	public void test2(){
		try {
			File path = new File("C:\\yrtz\\test\\java-mapper\\java-mapper");
			//获得指定路径下及子目录下的所有jar文件
			List<File> list = (List<File>) FileUtils.listFiles(path, new String[] { "jar" }, true);
			List<String> lists = new ArrayList<String>();
			if(list != null){
				String regex = ".+\\.properties";
//				String regex = ".+\\.ini.+";
				//后的jar中满足正则的所有文件
				for(File file : list){
					System.out.println(file.getAbsolutePath());
					try {
						Pattern p = Pattern.compile(regex);
						JarFile jarFile = new JarFile(file.getAbsolutePath());
						Enumeration<JarEntry> entries = jarFile.entries();
						while (entries.hasMoreElements()) {
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							System.out.println(name);
							Matcher m = p.matcher(name);
							if (m.matches()) {
								lists.add(name);
							}
						}
						jarFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(lists != null){
				for(String tmp : lists){
					System.out.println("properties文件列表：" + tmp);
					//加载指定properties文件中所有属性
					if(tmp.indexOf("log4j") > -1){
						InputStream in = this.getClass().getClassLoader().getResourceAsStream(tmp);
						if (in != null) {
							Properties p = new Properties();
							p.load(in);
							for(Object key : p.keySet()){
								System.out.println((String)key + "\t:\t" + p.getProperty((String)key));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
