package com.sms.service;

public class Auth {
	
	private final static String username = "admin";
	private final static String password = "admin";
	private static boolean isAuthorized = false;
	
	private static boolean isCredentialsValid(String usr,String pass) {
		return username.equals(usr) && password.equals(pass);
	}

	private static boolean isAuthorized() {
		return isAuthorized;
	}

	
	
	
	
	
	
	
	

	
	
	

}
