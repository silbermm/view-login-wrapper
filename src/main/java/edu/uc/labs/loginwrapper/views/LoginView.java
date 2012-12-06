package edu.uc.labs.loginwrapper.views;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * 
 * @author Matt Silbernagel
 */
public interface LoginView {
	void showFrame() throws IOException;
	void closeFrame();
	JFrame getMainFrame();
}
