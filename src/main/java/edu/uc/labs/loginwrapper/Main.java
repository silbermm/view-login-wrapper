package edu.uc.labs.loginwrapper;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import edu.uc.labs.loginwrapper.views.LoginView;
import edu.uc.labs.loginwrapper.views.LoginViewImpl;


public class Main {

    public static void main(String[] args) {
        R = ResourceBundle.getBundle("MessagesBundle");
        parse(args);
        
        Config config = ConfigFactory.load();
        
        final LoginView l = new LoginViewImpl(config, R);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				l.showFrame();
			}
			
		});
    }

    @SuppressWarnings("static-access")
	private static void parse(String[] args) {
        CommandLineParser parser = new PosixParser();
        options.addOption(OptionBuilder.withArgName("h").withDescription("print this help message").withLongOpt("help").create("h"));
        options.addOption(OptionBuilder.withArgName("v").withDescription("Print version").withLongOpt("version").create("v"));
        options.addOption(OptionBuilder.withArgName("d").withDescription("Prints debugging information to the screen").withLongOpt("debug").create("d"));
        options.addOption(OptionBuilder.withArgName("c").hasArg().withDescription("Specify a config file").withLongOpt("configfile").create("c"));
        
        
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            printHelp();
        }
        if (cmd.hasOption("d")) {
            Logger.getRootLogger().setLevel(Level.DEBUG);
        }
        if (cmd.hasOption("h")) {
            printHelp();
        }
        if (cmd.hasOption("v")) {
            System.out.println(R.getString("application.name").toLowerCase() + " - " + R.getString("application.version"));
            System.exit(1);
        }
        if (cmd.hasOption("c")) {
            File newfile = new File(cmd.getOptionValue("c"));
            log.debug("setting config.file property to " + newfile.getAbsolutePath());
            System.setProperty("config.file", newfile.getAbsolutePath());
        }
    }

    /**
     * Print the help message
     */
    private static void printHelp() {
        HelpFormatter help = new HelpFormatter();
        help.printHelp(R.getString("application.name").toLowerCase(), options);
        System.exit(1);
    }
    
    private static final Logger log = Logger.getLogger(Main.class);
    private static ResourceBundle R;
    private static Options options = new Options();
    private static CommandLine cmd = null;
}

