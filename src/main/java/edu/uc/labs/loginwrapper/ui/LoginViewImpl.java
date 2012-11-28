package edu.uc.labs.loginwrapper.ui;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.typesafe.config.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.apache.log4j.Logger;

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

    @Override
    public void showFrame() {
        buildFrame();
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(mainFrame);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               LoginDialog ld = new LoginDialog(mainFrame, "the title");
               ld.setVisible(true);
            }
        });
    }

    private void buildFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);
        mainFrame.setBackground(Color.DARK_GRAY);      
        mainFrame.validate();
    }
    
     /**
     * Create and return a panel for the Title
     *
     * @return Component
     */
    private JComponent createTitlePanel() {
        FormLayout layout = new FormLayout(
                "left:125px:grow, center:pref, right:25px:grow",
                "center:74px, bottom:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.background(Color.WHITE);
        CellConstraints cc = new CellConstraints();
        //builder.add(new ImagePanel(), cc.xy(1, 1));
        JLabel title = new JLabel(R.getString("frame.largeTitle"));
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        builder.add(title, cc.xy(2, 1));
        return builder.getPanel();
    }
    
    
    private static final Logger log = Logger.getLogger(LoginViewImpl.class);
    private final Config config;
    private final ResourceBundle R;
    private static JFrame mainFrame;
}
