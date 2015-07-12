package com.demo.loginservice.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class DBCPFactory {

	private BasicDataSource bd;
	private Properties props;
	private InputStream is;
	private String userName;
	private String password;
	private String url;
	
	public DBCPFactory() {
		try {
			props = new Properties();
			logger.info("Getting db.properties file from classpath");
			is = getClass().getResourceAsStream("db.properties");
			logger.info("Loading db.properties file...");
			props.load(is);
			userName = props.getProperty("userName");
			password = props.getProperty("password");
			url = props.getProperty("url");
		} catch (IOException e) {
			logger.debug("Error thrown while reading db.properties file: ",e);
		}
	}
	
	
	
	/**
	 * Setting db properties to Datasource 
	 * @return Datasource object
	 */
	public DataSource getDataSource() {
		bd = new BasicDataSource();
		logger.info("Setting database prpoerties to datasource");
		bd.setUsername(userName);
		bd.setPassword(password);
		bd.setUrl(url);
		
		return bd;
		
		
	}
	final static Logger logger  = Logger.getLogger(DBCPFactory.class);
}
