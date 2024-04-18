package com.sms.service;

public class Auth {
	
	private final static String username = "admin";
	private final static String password = "admin";
	private static boolean isAuthorized = false;
	
	private static boolean isCredentialsValid(String usr,String pass) {
		return username.equals(usr) && password.equals(pass);
	}

	public static boolean isAuthorized() {
		return isAuthorized;
	}

	
	
	
	
	public static boolean login(String usr,String pass) {
		if(isCredentialsValid(usr, pass)) {
			isAuthorized = true;
			return isAuthorized;
		}
		
		return false;
	}
	
	
	

	
	
	

}
