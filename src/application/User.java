package application;

import org.json.simple.*;

public class User {
	
	
	private String Name;
	//Login credentials
	private String userName;
	private String passWord;
	
	
	
	User(String un, String pw){
		this.userName = un;
		this.passWord = pw;
	}
	//Getters and Setters
	String getName() {
		return this.Name;
	}
	void setName(String name) {
		this.Name = name;
	}
	String getUsername() {
		return this.userName;
	}
	void setUsername(String un) {
		this.userName = un;
	}
	String getPassword() {
		return this.passWord;
	}
	void setPassword(String pw) {
		this.passWord = pw;
	}
	
}
