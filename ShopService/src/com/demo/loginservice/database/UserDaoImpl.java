package com.demo.loginservice.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class UserDaoImpl implements UserDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement ps;
	private Map<String,String> credMap;
	private ResultSet res;
	
	public UserDaoImpl() {
		try {
			ds = new DBCPFactory().getDataSource();
			conn = ds.getConnection();
		} catch (SQLException e) {
			logger.debug("Error thrown while getting connection for datasource: ",e);
		}
	}
	
	public void saveCredentials(String userName, String password) {
		try {
			logger.info("Starting insertion of data in to credentials table");
			ps = conn.prepareStatement("Insert into ws_credentials(userName, password, creationDate)values(?,?,?)");
			ps.setString(0, userName);
			ps.setString(1, password);
			ps.setDate(2, new Date(System.currentTimeMillis()));
			int id = ps.executeUpdate();
			logger.info("Insertion of data into credentials table completed succesfully");
			
			if(id>0) {
				logger.info("Starting insertion of data into purchases table");
				ps = conn.prepareStatement("Insert into purchases(userId) select cred.userId from ws_credentials as cred where cred.userName = ? and cred.password = ?");
				ps.setString(0, userName);
				ps.setString(1, password);
				ps.executeUpdate();
				logger.info("Insertion of data into purchases table completed succesfully");
			}else {
				logger.info("Credentials were not saved, thus purchases table is not populated");
			}
			
		} catch (SQLException e) {
			logger.debug("Error thrown while saving credentials/purchases: ",e);
		}
		
	}

	public Map<String, String> getCredentials(String userName, String password) {
		credMap = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement("Select * from ws_credentials where userName = ? and password =?");
			ps.setString(0, userName);
			ps.setString(1, password);
			res = ps.executeQuery();
			for(int i=1;i<=res.getMetaData().getColumnCount();i++) {
				credMap.put(res.getMetaData().getColumnLabel(i),res.getString(res.getMetaData().getColumnLabel(i)));
			}
			
		} catch (SQLException e) {
			logger.debug("Error was caused during getCredentials service statement: ",e);
		}
		return credMap;
	}
	
	

	public void saveUserDetails(String name, String age, String sex,String userId) {
		try {
			logger.info("Starting insertion of data into UserDetails table");
			ps = conn.prepareStatement("Insert into user_details(name,age,sex,userId,purchaseId) values(?,?,?,?,(select pur.purchaseId from purchases as pur where pur.userId= ?))");
			ps.setString(0, name);
			ps.setInt(1, Integer.parseInt(age));
			ps.setString(2, sex);
			ps.setInt(3, Integer.parseInt(userId));
			ps.setInt(4, Integer.parseInt(userId));
			ps.executeUpdate();
			logger.info("Insertion of data into UserDetails completed successfully");
		} catch (SQLException e) {
			logger.debug("This error was caused while saving User Details: ",e);
		}
	}
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);
}
