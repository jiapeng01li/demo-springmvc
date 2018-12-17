package com.jplee.springmvc.common;

import java.io.InputStream;
import java.net.URL;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class TestDataSource {

	public static void loadLoggerContext() {
		  System.getProperties().put("logback.configurationFile", "./logback.xml"); 
		  LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory(); 
		  StatusPrinter.setPrintStream(System.err); 
		  StatusPrinter.print(lc); 
		 }
		 public static void main(String[] args) { 		
		  try { 
		   loadLoggerContext(); 
//		   FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/spring/applicationContext-datasource.xml"); 
		   ClassLoader classLoader = TestDataSource.class.getClassLoader();
			 URL resource = classLoader.getResource("spring/applicationContext-datasource.xml");		
			 String path = resource.getPath();	
			 System.out.println(path);		
			 InputStream resourceAsStream = classLoader.getResourceAsStream("spring/applicationContext-datasource.xml");
		  
		  } catch (Exception e) { 
		   System.out.println(e); 
		  } 
		 }

}
