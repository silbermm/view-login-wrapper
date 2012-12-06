package edu.uc.labs.loginwrapper.service;

public interface ViewService {

	/**
	 * run the view client
	 * 
	 * @param username
	 *            the username to connect with
	 * @param password
	 *            the password to connect with
	 * @return the exit code of the client
	 */
	int runViewClient(String username, String password);
	boolean isLocalAccount(String username, String password);
	int runTerminal(String username);
}
