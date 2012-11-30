package edu.uc.labs.loginwrapper.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Provides a JDialog rendered as a login window
 * 
 * @author Matt Silbernagel
 */
public class LoginDialog extends JDialog {

	public LoginDialog(JFrame mainFrame, Image image, ResourceBundle R) {
		super(mainFrame, "title", true);
		this.R = R;
		this.image = image;
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(mainFrame);
		JPanel loginPanel = new LoginPanel();
		loginPanel.add(build());
		this.setContentPane(loginPanel);
		setResizable(false);
	}
	
	private class LoginPanel extends JPanel {
		
		public LoginPanel(){
			//this.setSize(WIDTH, HEIGHT);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image,0,0,getWidth(),getHeight(),this);
		}

	}

	private JPanel build() {
		FormLayout layout = new FormLayout(
				"38px,right:58px,14px,left:276px,33px", // 6 columns
				"160px,28px,9px,28px,118px"// 6 rows
		);
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.add(new JLabel(R.getString("ui.login.username")), cc.xy(2, 2));
		builder.add(new JTextField(), cc.xy(4, 2));
		return builder.getPanel();
	}
		
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		log.debug("in the paint components method!");
		g.drawImage(image,0,0,getWidth(),getHeight(),this);
	}
	

	private static final long serialVersionUID = 7610511076522587269L;
	private static final Logger log = Logger.getLogger(LoginDialog.class);
	
	private static final int WIDTH = 419;
	private static final int HEIGHT = 342;
	
	private final ResourceBundle R;
	private final Image image;

}
