package edu.uc.labs.loginwrapper;

import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import com.typesafe.config.Config;

import edu.uc.labs.loginwrapper.views.LoginView;
import edu.uc.labs.loginwrapper.views.LoginViewImpl;

public class LoginManager implements Manager {

	public LoginManager(Config config, ResourceBundle R) {
		this.config = config;
		this.R = R;
	}

	@Override
	public void init() {
		final LoginView l = new LoginViewImpl(config, R);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				l.showFrame();
			}
			
		});
	}

	private final Config config;
	private final ResourceBundle R;
}
