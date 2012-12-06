package edu.uc.labs.loginwrapper.models;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.typesafe.config.Config;

import edu.uc.labs.loginwrapper.utils.ViewWorker;
import edu.uc.labs.loginwrapper.views.LoginDialog;

public class LoginAction extends AbstractAction {

	private static final long serialVersionUID = 4760055143549917553L;
	private LoginDialog ld;
	private static ResourceBundle R = ResourceBundle
			.getBundle("MessagesBundle");
	private Config config;

	public LoginAction(LoginDialog ld, Config config) {
		super(R.getString("ui.login.btn.ok"));
		this.ld = ld;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ld.getUsername().isEmpty()) {
			showError("Please enter a username/password");
		} else if (ld.getPassword().isEmpty()) {
			showError("Please enter a username/password");
		} else {
			String username = ld.getUsername();
			String password = ld.getPassword();
			
			SwingWorker<Integer, Integer> viewWorker = new ViewWorker(ld, username, password,  config);
			viewWorker.execute();
			// Clear the username and password field
			ld.clearUsername();
			ld.clearPassword();
		}
		
	}

	public void showError(final String message) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				LoginDialog.showError(message);
			}

		});

	}

}
