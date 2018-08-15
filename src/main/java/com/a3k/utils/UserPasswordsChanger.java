package com.a3k.utils;

public class UserPasswordsChanger {
	private static String login;

	public UserPasswordsChanger(String login) {
		UserPasswordsChanger.login = login;
	}

	public static String checkAndChangePassword() {
		String result = "";
		
		switch (login) {
		case "kb.ref":
			result = "joke";			
			break;

		case "uk.kba":
			result = "fire";
			break;
			
		case "uk.super":
			result = "game";
			break;
			
		case "jbonilla":
			result = "golf";
			break;
			
		case "ref.kbadmin":
			result = "ladder";
			break;
		
		case "artem.superadmin":
			result = "hope";
			break;
			
		case "uskba13443.alexdemo":
			result = "January";
			break;
			
		case "uskba.alex":
			result = "job";
			break;
			

		default:
			break;
		}
		
		
		
		return result;
	}

}
