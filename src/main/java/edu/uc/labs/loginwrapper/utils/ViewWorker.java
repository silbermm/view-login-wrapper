package edu.uc.labs.loginwrapper.utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.typesafe.config.Config;

import edu.uc.labs.loginwrapper.service.ViewService;
import edu.uc.labs.loginwrapper.service.ViewServiceImpl;
import edu.uc.labs.loginwrapper.views.LoginDialog;

public class ViewWorker extends SwingWorker<Integer, Integer> {

	private static final Logger log = Logger.getLogger(ViewWorker.class);

	private LoginDialog ld;
	private ViewService viewService;
	private Config config;
	private String username, password;

	public ViewWorker(LoginDialog ld, String username, String password,
			Config config) {
		this.ld = ld;
		this.config = config;
		this.viewService = new ViewServiceImpl(config);
		this.username = username;
		this.password = password;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// Let's start the time consuming task
		log.debug("Starting the background process");
		// Thread.sleep(10000);
		if (viewService.isLocalAccount(username, password)) {
			ld.setVisible(false);
			int result = viewService.runTerminal(username);
			// When we get here, reopen the LoginDialog
			ld.pack();
			ld.setVisible(true);
			return result;
		} else {
			int result = viewService.runViewClient(username, password);
			return result;
		}
	}

	@Override
	protected void done() {
		log.debug("Finally Done!");
		try {
			int returnVal = get();
			log.debug("got a return value of " + returnVal);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void process(List<Integer> pInt) {
		// This method is called after tje call to publish is finished (I think)
		log.debug("in the process method... ");
	}

}
