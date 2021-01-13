import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login implements ActionListener {

    private static JTextField userText;
    private static JPasswordField passwordText;

    private static JButton loginButton;
    private static JButton registerButton;
    protected static JFrame loginFrame = new JFrame();
    protected static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    protected static ImageIcon titleIcon;


    public static void loginFrame() {

        loginFrame.setSize(375, 230);
        loginFrame.setTitle("eHealthcare Login");
        titleIcon = new ImageIcon("eHealthcareFrameIcon1.png");
        loginFrame.setIconImage(titleIcon.getImage());
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocation(dim.width / 2 - loginFrame.getSize().width / 2, dim.height / 2 - loginFrame.getSize().height / 2);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username :");
        userLabel.setBounds(20, 20, 80, 25);
        panel.add(userLabel);

        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(20, 50, 80, 25);
        panel.add(passwordLabel);


        userText = new JTextField();
        userText.setBounds(120, 20, 210, 25);
        panel.add(userText);

        passwordText = new JPasswordField();
        passwordText.setBounds(120, 50, 210, 25);
        panel.add(passwordText);


        loginButton = new JButton("Login");
        loginButton.setBounds(20, 100, 80, 25);
        loginButton.setLocation(loginFrame.getWidth() / 2 - loginButton.getWidth() / 2 - 5, passwordText.getY() + 50);
        loginButton.addActionListener(new Login());
        panel.add(loginButton);


        registerButton = new JButton("Register");
        registerButton.setBounds(20, 135, 100, 25);
        registerButton.setLocation(loginFrame.getWidth() / 2 - registerButton.getWidth() / 2 - 5, loginButton.getY() + 40);
        registerButton.addActionListener(new Login());
        panel.add(registerButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        try {
            if (e.getSource() == Login.loginButton) {
                Databaseconnection databaseconnection = new Databaseconnection();
                ResultSet res = databaseconnection.getUser(userText.getText());
                if(!res.next()) {
                    JOptionPane.showMessageDialog(loginFrame,"eMail-address not found", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    String password = String.valueOf(passwordText.getPassword());
                    int check = -1;
                    while (check == -1) {
                        if (e.getSource() == Login.loginButton) {
                            if (userText.getText().equals("") || password.equals("")) {
                                JOptionPane.showMessageDialog(loginFrame, "Fields cannot be empty! Please enter your data.");
                                break;
                            } else if ((org.mindrot.jbcrypt.BCrypt.checkpw(password,res.getString(2)))) {
                                JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                                check = 0;
                                loginFrame.dispose();
                            } else {
                                System.out.println(User.hashPassword(password));
                                JOptionPane.showMessageDialog(loginFrame, "Login unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }
                    }

                }
            } else if (e.getSource() == Login.registerButton) {
                Registration.userRegistration();
            }
        } catch(Exception penis){
            System.out.println(penis.getMessage());
        }
    }
}
