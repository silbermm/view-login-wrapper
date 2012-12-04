package edu.uc.labs.loginwrapper.views;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.typesafe.config.Config;

/**
 * 
 * @author Matt Silbernagel
 */
public class LoginViewImpl implements LoginView {

	public LoginViewImpl(final Config config, final ResourceBundle R) {
		this.config = config;
		this.R = R;
		PlasticLookAndFeel laf = new PlasticXPLookAndFeel();
		PlasticLookAndFeel.setCurrentTheme(new ExperienceBlue());
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException e) {
			log.debug("Unsupported Look and Feel");
		}
	}

	public void showFrame() {
		buildFrame();
		final Image image = new ImageIcon(getClass().getResource("/Login4.png")).getImage();
		GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().setFullScreenWindow(mainFrame);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LoginDialog login = new LoginDialog(mainFrame, image, config, R);
				login.pack();
				login.validate();
				login.setVisible(true);
			}
		});

	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	private void buildFrame() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setBackground(new Color(55, 50, 50));
		mainFrame.pack();
		mainFrame.validate();
	}

	private static final Logger log = Logger.getLogger(LoginViewImpl.class);
	private final Config config;
	private final ResourceBundle R;
	private static JFrame mainFrame;
}
