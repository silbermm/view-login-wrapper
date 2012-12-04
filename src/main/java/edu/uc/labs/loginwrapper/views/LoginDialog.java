package edu.uc.labs.loginwrapper.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.typesafe.config.Config;

import edu.uc.labs.loginwrapper.models.LoginAction;

/**
 * Provides a JDialog rendered as a login window
 * 
 * @author Matt Silbernagel
 */
public class LoginDialog extends JDialog {

	public LoginDialog(JFrame mainFrame, Image image, Config config, ResourceBundle R) {
		super(mainFrame, "title", true);
		this.R = R;
		this.image = image;
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(mainFrame);
		
		loginAction = new LoginAction(this, config);

		
		JPanel loginPanel = new LoginPanel();
		loginPanel.add(build());
		this.setContentPane(loginPanel);
		setResizable(false);
		thisDialog = this;
	}



	private JPanel build() {
		FormLayout layout = new FormLayout(
				"30px,right:68px,14px,97px,80px,9px,80px,33px",
				"150px,28px,9px,28px,60px,58px");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		// Create the Labels
		JLabel usernameLbl = new JLabel(R.getString("ui.login.username"));
		usernameLbl.setForeground(Color.WHITE);
		JLabel passLbl = new JLabel(R.getString("ui.login.password"));
		passLbl.setForeground(Color.WHITE);

		// Create the TextFields
		usernameField = new JTextField(50);
		usernameField.setMinimumSize(new Dimension(266, 28));
		passwordField = new JPasswordField(50);
		passwordField.setMinimumSize(new Dimension(266, 28));

		// Create the Buttons
		JButton loginBtn = new JButton(loginAction);
		JButton passBtn = new JButton(R.getString("ui.login.btn.reset"));

		builder.add(usernameLbl, cc.xy(2, 2));
		builder.add(usernameField, cc.xyw(4, 2, 4));
		builder.add(passLbl, cc.xy(2, 4));
		builder.add(passwordField, cc.xyw(4, 4, 4));

		builder.add(loginBtn, cc.xy(7, 5));
		builder.add(passBtn, cc.xy(5, 5));

		return builder.getPanel();
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		log.debug("in the paint components method!");
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public static void showError(final String message){
		JOptionPane.showMessageDialog(thisDialog, message);
	}
	
	public void clearUsername(){
		this.usernameField.setText("");
	}
	
	public void clearPassword(){
		this.passwordField.setText("");
	}
	
	public String getUsername(){
		return this.usernameField.getText();
	}
	
	public String getPassword(){
		char[] passwordByte = passwordField.getPassword();
		return new String(passwordByte);
	}
	
	private class LoginPanel extends JPanel {

		private static final long serialVersionUID = 349413799529915814L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private static final long serialVersionUID = 7610511076522587269L;
	private static final Logger log = Logger.getLogger(LoginDialog.class);

	private static JDialog thisDialog;
	
	private static final int WIDTH = 419;
	private static final int HEIGHT = 342;

	private final ResourceBundle R;
	private final Image image;

	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private Action loginAction;

}
