package edu.uc.labs.loginwrapper.ui;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matt Silbernagel
 */
public class LoginDialog extends JDialog {
    
    public LoginDialog(JFrame mainFrame, String title){
       super(mainFrame, title, true);
       setSize(300, 300);
       setLocationRelativeTo(mainFrame);
       
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 240;
                    final int G = 240;
                    final int B = 240;
                    
                    Paint p =
                        new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                            0.0f, getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
       
       //panel.setOpaque(true);
       cp = getContentPane();
       this.setContentPane(panel);
       FormLayout layout = new FormLayout("pref:grow",      // 1 column 
                "pref,4dlu,top:pref:grow,pref,bottom:pref"); // 3 rows
        PanelBuilder builder = new PanelBuilder(layout);
        builder.border(Borders.DIALOG);
        CellConstraints cc = new CellConstraints();
        
        builder.add(new JLabel("test"), cc.xy(1, 1));
        builder.add(new JButton("test"), cc.xy(1, 3));
        //panel.add(builder.getPanel());
        this.add(new JButton("testing3"));
        this.add(builder.getPanel());
        // Find out how not to lock the keys in the car!!!
        //setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    
    private Container cp;

}
