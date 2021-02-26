package games.midhedava.client.gui;

import games.midhedava.client.midhedava;
import games.midhedava.client.MidhedavaClient;
import games.midhedava.client.update.ClientGameConfiguration;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import marauroa.client.ariannexpTimeoutException;

/**
 * Summary description for CreateAccountDialog
 *
 */
public class CreateAccountDialog extends JDialog {

    private static final long serialVersionUID = 4436228792112530975L;

    private JLabel usernameLabel;

    private JLabel serverLabel;

    private JLabel serverPortLabel;

    private JLabel passwordLabel;

    private JLabel passwordretypeLabel;

    private JLabel emailLabel;

    private JLabel sexLabel;

    private JLabel raceLabel;

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JPasswordField passwordretypeField;

    private JTextField emailField;

    private JTextField serverField;

    private JTextField serverPortField;

    private JComboBox sexField;

    private JComboBox raceField;

    private JButton createAccountButton;

    private JPanel contentPane;

    private MidhedavaClient client;

    private Frame owner;

    public CreateAccountDialog(Frame owner, MidhedavaClient client) {
        super(owner, true);
        this.client = client;
        this.owner = owner;
        initializeComponent();
        this.setVisible(true);
    }

    /**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Windows Form Designer. Otherwise, retrieving design
	 * might not work properly. Tip: If you must revise this method, please
	 * backup this GUI file for JFrameBuilder to retrieve your design properly
	 * in future, before revising this method.
	 */
    private void initializeComponent() {
        String[] sexItems = { "male", "female" };
        String[] raceItems = { "roman", "dacian", "celt" };
        serverLabel = new JLabel("Server name");
        serverField = new JTextField(ClientGameConfiguration.get("DEFAULT_SERVER"));
        serverField.setEditable(true);
        serverPortLabel = new JLabel("Server port");
        serverPortField = new JTextField(ClientGameConfiguration.get("DEFAULT_PORT"));
        usernameLabel = new JLabel("Choose a username");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Choose a password");
        passwordField = new JPasswordField();
        passwordretypeLabel = new JLabel("Retype password");
        passwordretypeField = new JPasswordField();
        emailLabel = new JLabel("E-mail address");
        emailField = new JTextField();
        sexLabel = new JLabel("Character's sex");
        sexField = new JComboBox(sexItems);
        raceLabel = new JLabel("Character's race");
        raceField = new JComboBox(raceItems);
        createAccountButton = new JButton();
        contentPane = (JPanel) this.getContentPane();
        createAccountButton.setText("Create Account");
        createAccountButton.setMnemonic(KeyEvent.VK_C);
        createAccountButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                createAccountButton_actionPerformed(e, false);
            }
        });
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(serverLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(serverField, c);
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(serverPortLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(serverPortField, c);
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(usernameLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(usernameField, c);
        usernameField.requestFocusInWindow();
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(passwordretypeLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(passwordretypeField, c);
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(emailLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(emailField, c);
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(sexLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(sexField, c);
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(raceLabel, c);
        c.gridx = 1;
        c.gridy = 7;
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(raceField, c);
        c.gridx = 1;
        c.gridy = 8;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 4, 4, 4);
        contentPane.add(createAccountButton, c);
        this.setTitle("Create New Account");
        this.setResizable(false);
        this.setSize(new Dimension(350, 367));
        this.setLocationRelativeTo(owner);
    }

    private void createAccountButton_actionPerformed(ActionEvent e, boolean saveLoginBoxStatus) {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());
        final String passwordretype = new String(passwordField.getPassword());
        if (!password.equals(passwordretype)) {
            JOptionPane.showMessageDialog(owner, "The passwords do not match. Please retype both.", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        final String email = emailField.getText();
        final String server = serverField.getText();
        int port = 32160;
        final int finalPort;
        final ProgressBar progressBar = new ProgressBar(owner);
        try {
            port = Integer.parseInt(serverPortField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner, "That is not a valid port number. Please try again.", "Invalid Port", JOptionPane.WARNING_MESSAGE);
            return;
        }
        finalPort = port;
        Thread m_connectionThread = new Thread() {

            @Override
            public void run() {
                progressBar.start();
                setVisible(false);
                try {
                    client.connect(server, finalPort, true);
                    progressBar.step();
                } catch (Exception ex) {
                    progressBar.cancel();
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Midhedava cannot connect to the Internet. Please check that your connection is set up and active, then try again.");
                    ex.printStackTrace();
                    return;
                }
                try {
                    if (client.createAccount(username, password, email) == false) {
                        String result = client.getEvent();
                        if (result == null) {
                            result = "The server is not responding. Please check that it is online, and that you supplied the correct details.";
                        }
                        progressBar.cancel();
                        setVisible(true);
                        JOptionPane.showMessageDialog(owner, result, "Error Creating Account", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        progressBar.step();
                        progressBar.finish();
                        client.setUserName(username);
                    }
                } catch (ariannexpTimeoutException ex) {
                    progressBar.cancel();
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Unable to connect to server to create your account. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.", "Error Creating Account", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    if (client.login(username, password) == false) {
                        String result = client.getEvent();
                        if (result == null) {
                            result = "Unable to connect to server. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.";
                        }
                        progressBar.cancel();
                        setVisible(true);
                        JOptionPane.showMessageDialog(owner, result, "Error Logging In", JOptionPane.ERROR_MESSAGE);
                    } else {
                        progressBar.step();
                        progressBar.finish();
                        setVisible(false);
                        owner.setVisible(false);
                        midhedava.doLogin = true;
                    }
                } catch (ariannexpTimeoutException ex) {
                    progressBar.cancel();
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Unable to connect to the server. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.", "Error Logging In", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        m_connectionThread.start();
    }
}
