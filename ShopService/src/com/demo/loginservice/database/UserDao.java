package com.demo.loginservice.database;

import java.util.Map;

public interface UserDao {
	public String saveCredentials(String userName, String password);
	public Map<String,String> getCredentials(String userName, String password);
	public void saveUserDetails(String name, String age, String sex, String userId);
}
