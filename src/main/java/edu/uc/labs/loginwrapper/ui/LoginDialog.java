package edu.uc.labs.loginwrapper.ui;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Matt Silbernagel
 */
public class LoginDialog extends JDialog {
    
    public LoginDialog(JFrame mainFrame, String title){
       super(mainFrame, title, true);
       setSize(300, 300);
       setLocationRelativeTo(mainFrame);
       cp = getContentPane();
       
       FormLayout layout = new FormLayout("pref:grow",      // 1 column 
                "pref,4dlu,top:pref:grow,pref,bottom:pref"); // 3 rows
        PanelBuilder builder = new PanelBuilder(layout);
        builder.border(Borders.DIALOG);
        CellConstraints cc = new CellConstraints();
        
        builder.add(new JLabel("test"), cc.xy(1, 1));
        builder.add(new JButton("test"), cc.xy(1, 3));
        cp.add(builder.getPanel());
       
        // Find out how not to lock the keys in the car!!!
        //setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    
    private Container cp;

}
