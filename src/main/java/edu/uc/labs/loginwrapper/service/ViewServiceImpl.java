package edu.uc.labs.loginwrapper.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.log4j.Logger;

import com.typesafe.config.Config;

public class ViewServiceImpl implements ViewService {

	private static final Logger log = Logger.getLogger(ViewServiceImpl.class);
	private Config config;
	private static ResourceBundle R = ResourceBundle
			.getBundle("MessagesBundle");

	public ViewServiceImpl(Config config) {
		this.config = config;
	}

	@Override
	public int runViewClient(String username, String password) {
		if (!isLocalAccount(username, password)) {
			log.debug("This is not a local account... trying to login via VMWare View");
			String view_bin = config.getString("view.binary.path");
		} else {
			log.debug("Able to login with a local account, exit this app and logout of the machine");
			
			return -1;
		}
		// Check credentials on local system first
		return 0;
	}

	/**
	 * Uses mkpasswd to create the password hash and checks the returned hash
	 * against the /etc/shadow file
	 * 
	 * @param username
	 *            the username to check
	 * @param password
	 *            the password to check
	 * @return
	 * @throws IOException
	 * @throws ExecuteException
	 */
	private boolean isLocalAccount(String username, String password) {
		int exitValue = -1;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		HashMap<String, File> map = new HashMap<String, File>();
		map.put("script", new File(config.getString("perl.shadow.script")));
		try {
			CommandLine cmdLine = new CommandLine(config.getString("perl.binary.path"));
			cmdLine.addArgument("${script}");
			cmdLine.addArgument(username);
			cmdLine.addArgument(password);
			cmdLine.setSubstitutionMap(map);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			Executor executor = new DefaultExecutor();
			executor.setStreamHandler(streamHandler);
			executor.execute(cmdLine, resultHandler);
			try {
				resultHandler.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			exitValue = resultHandler.getExitValue();
			log.debug("exit code: " + exitValue);
		} catch (ExecuteException e) {
			log.error("Execute Exception: " + e.getMessage());
			return false;
		} catch (IOException e) {
			log.error("IO Exception: " + e.getMessage());
			return false;
		}
		boolean returnVal = (exitValue == 0) ? true : false;
		return returnVal;
	}
}
