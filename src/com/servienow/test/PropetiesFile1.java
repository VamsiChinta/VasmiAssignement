package com.servienow.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.Test;

public class PropetiesFile1 {
	public static Properties CONFIG=null;
	 
	 public PropetiesFile1() throws Exception{

	  CONFIG=new Properties();
	  FileInputStream fn=new FileInputStream("C://Users//eigenaar//workspace//ServiceNow//configuration.properties");
	  CONFIG.load(fn);
	 }
	public String geturl(){
		 return CONFIG.getProperty("Url");
	 }
	public String getlogin_id(){
		 return CONFIG.getProperty("login_id");
	 }
	public String getlogin_pswd(){
		 return CONFIG.getProperty("login_pswd");
	 }
}

