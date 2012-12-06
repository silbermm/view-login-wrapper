package edu.uc.labs.loginwrapper.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
	
	public ViewServiceImpl(Config config) {
		this.config = config;
	}
	
	@Override
	public int runViewClient(String username, String password) {
		log.debug("This is not a local account... trying to login via VMWare View");
		try {
			CommandLine cmdLine = new CommandLine(
					config.getString("view.binary.path"));
			if (config.hasPath("view.option.server")) {
				log.debug("server option is set");
				cmdLine.addArgument("-s");
				cmdLine.addArgument(config.getString("view.option.server"));
			}
			cmdLine.addArgument("-u");
			cmdLine.addArgument(username);
			cmdLine.addArgument("-p");
			cmdLine.addArgument(password);
			if (config.hasPath("view.option.domain")) {
				log.debug("domain option is set");
				cmdLine.addArgument("-d");
				cmdLine.addArgument(config.getString("view.option.domain"));
			}
			if (config.hasPath("view.option.desktop")) {
				log.debug("desktop option is set");
				cmdLine.addArgument("--desktopName");
				cmdLine.addArgument(config.getString("view.option.desktop"));
			}
			if(config.getBoolean("view.option.nonInteractive")){
				log.debug("non interactive options is set");
				cmdLine.addArgument("-q");
			}
			if(config.getBoolean("view.option.fullscreen")){
				log.debug("fullscreen options is set");
				cmdLine.addArgument("--fullscreen");
			}
			if(config.getBoolean("view.option.once")){
				cmdLine.addArgument("--once");
			}
			if(config.getBoolean("view.option.nomenu")){
				cmdLine.addArgument("--nomenubar");
			}
			log.debug(cmdLine.toString());
			
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			Executor executor = new DefaultExecutor();
			executor.execute(cmdLine, resultHandler);
			resultHandler.waitFor();

			return (resultHandler.getExitValue());

		} catch (InterruptedException e) {
			log.error(e.getMessage());
		} catch (ExecuteException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return -1;
	}

	public int runTerminal(String username) {
		log.debug("Able to login with a local account, show an terminal and close the mainFrame... ");
		try {
			CommandLine cmdLine = new CommandLine("su");
			cmdLine.addArgument("-c");
			cmdLine.addArgument(config.getString("xterm.binary.path"));
			cmdLine.addArgument("-");
			cmdLine.addArgument(username);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			Executor executor = new DefaultExecutor();
			executor.execute(cmdLine, resultHandler);
			resultHandler.waitFor();
			int exitValue = resultHandler.getExitValue();
			log.debug("exit code: " + exitValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecuteException e) {
			log.error("Execute Exception: " + e.getMessage());
			return -1;
		} catch (IOException e) {
			log.error("IO Exception: " + e.getMessage());
			return -1;
		}
		return -1;
	}

	/**
	 * Uses usercheck.pl to check username/password against the /etc/shadow file
	 * 
	 * @param username
	 *            the username to check
	 * @param password
	 *            the password to check
	 * @return
	 */
	public boolean isLocalAccount(String username, String password) {
		int exitValue = -1;
		log.debug("password = " + password);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		HashMap<String, File> map = new HashMap<String, File>();
		map.put("script", new File(config.getString("perl.shadow.script")));
		try {
			CommandLine cmdLine = new CommandLine(
					config.getString("perl.binary.path"));
			cmdLine.addArgument("${script}");
			cmdLine.addArgument(username);
			cmdLine.addArgument(password);
			cmdLine.setSubstitutionMap(map);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			Executor executor = new DefaultExecutor();
			executor.setStreamHandler(streamHandler);
			log.debug("execute: " + cmdLine.toString());
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
		return (exitValue == 0) ? true : false;
	}

	
}
