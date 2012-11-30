package edu.uc.labs.loginwrapper.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ResourceBundle;

import javax.swing.Action;
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

	public LoginDialog(JFrame mainFrame, ResourceBundle R) {
		super(mainFrame, "title", true);
		this.R = R;
		setSize(400, 400);
		setLocationRelativeTo(mainFrame);
		JPanel p = build();
		
		Container cp = getContentPane();
		cp.setSize(400, 400);
		cp.add(p);
		setResizable(false);
		pack();
		validate();
	}
	
	
	private JPanel build(){
		FormLayout layout = new FormLayout("right:pref,4dlu,left:pref:grow,50dlu,4dlu,50dlu",	// 6 columns
				"top:pref,40dlu,40dlu,40dlu,4dlu,bottom:40dlu"// 6 rows
				); 
		PanelBuilder builder = new PanelBuilder(layout);
		builder.border(Borders.DIALOG);
		CellConstraints cc = new CellConstraints();
		builder.add(new JLabel(R.getString("ui.login.title")), cc.xyw(1, 1, 6));
		return builder.getPanel();
	}

	private JComponent getLoginPane() {
		FormLayout layout = new FormLayout("pref:grow,4dlu,pref:grow", 	// 3 columns
				"pref,4dlu,top:pref:grow"); 						// 3 rows
		PanelBuilder builder = new PanelBuilder(layout);
		builder.border(Borders.DIALOG);
		CellConstraints cc = new CellConstraints();
		
		JTextField username = new JTextField();
		
		
		builder.add(new JLabel(R.getString("ui.login.username")), cc.xy(1, 1));
		builder.add(new JTextField(), cc.xy(3, 1) );
		
		builder.add(new JLabel(R.getString("ui.login.password")), cc.xy(1, 3));
		builder.add(new JTextField(), cc.xy(3, 3));
		
		return builder.getPanel();
	}

	private JComponent getButtonPane() {
		FormLayout layout = new FormLayout("pref:grow", // 1 column
				"pref,4dlu,top:pref:grow,pref,bottom:pref"); // 3 rows
		PanelBuilder builder = new PanelBuilder(layout);
		builder.border(Borders.DIALOG);
		CellConstraints cc = new CellConstraints();
		return builder.getPanel();
	}

	private JComponent getMessagePane() {
		return rootPane;
	}

	private static final long serialVersionUID = 7610511076522587269L;
	private static final Logger log = Logger.getLogger(LoginDialog.class);
	private final ResourceBundle R;

}
