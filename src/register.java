import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class register implements ActionListener {

    private static JFrame registerFrame;
    private static JTextField userRegistrationSurname;
    private static JTextField userRegistrationLastName;
    private static JTextField userRegistrationAddress;
    private static JTextField userRegistrationStreet;
    private static JTextField userRegistrationPLZ;
    private static JTextField userRegistrationCity;

    public static void registration() {

        login.loginFrame.setEnabled(false);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);

        registerFrame = new JFrame();

        registerFrame.setSize(470, 500);
        registerFrame.setLocation(login.dim.width / 2 - registerFrame.getSize().width / 2, login.dim.height / 2 - registerFrame.getSize().height / 2);
        registerFrame.setResizable(false);
        registerFrame.setTitle("Register");
        registerFrame.setIconImage(login.titleIcon.getImage());
        registerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                login.loginFrame.setEnabled(true);
            }
        });


        JLabel surName = new JLabel("Name:");
        surName.setBounds(40, 50, 120, 25);
        registerPanel.add(surName);
        userRegistrationSurname = new JTextField();
        userRegistrationSurname.setBounds(250, 50, 165, 25);
        registerPanel.add(userRegistrationSurname);

        JLabel lastName = new JLabel("Last name:");
        lastName.setBounds(40, 80, 120, 25);
        registerPanel.add(lastName);
        userRegistrationLastName = new JTextField();
        userRegistrationLastName.setBounds(250, 80, 165, 25);
        registerPanel.add(userRegistrationLastName);

        JLabel emailAddress = new JLabel("E-Mail Address:");
        emailAddress.setBounds(40, 110, 120, 25);
        registerPanel.add(emailAddress);
        userRegistrationAddress = new JTextField();
        userRegistrationAddress.setBounds(250, 110, 165, 25);
        registerPanel.add(userRegistrationAddress);

        JLabel userStreet = new JLabel("Street:");
        userStreet.setBounds(40, 140, 120, 25);
        registerPanel.add(userStreet);
        userRegistrationStreet = new JTextField();
        userRegistrationStreet.setBounds(250, 140, 165, 25);
        registerPanel.add(userRegistrationStreet);

        JLabel userPLZ = new JLabel("Postal code:");
        userPLZ.setBounds(40, 170, 120, 25);
        registerPanel.add(userPLZ);
        userRegistrationPLZ = new JTextField();
        userRegistrationPLZ.setBounds(250, 170, 165, 25);
        registerPanel.add(userRegistrationPLZ);

        JLabel userCity = new JLabel("City:");
        userCity.setBounds(40, 200, 120, 25);
        registerPanel.add(userCity);
        userRegistrationCity = new JTextField();
        userRegistrationCity.setBounds(250, 200, 165, 25);
        registerPanel.add(userRegistrationCity);

        JLabel userPassword = new JLabel("Password");
        userPassword.setBounds(40, 230, 120, 25);
        registerPanel.add(userPassword);
        JPasswordField userRegistrationPassword1 = new JPasswordField();
        userRegistrationPassword1.setBounds(250, 230, 165, 25);
        registerPanel.add(userRegistrationPassword1);

        JLabel userPasswordValidation = new JLabel("Password again:");
        userPasswordValidation.setBounds(40, 260, 120, 25);
        registerPanel.add(userPasswordValidation);
        JPasswordField userRegistrationPassword2 = new JPasswordField();
        userRegistrationPassword2.setBounds(250, 260, 165, 25);
        registerPanel.add(userRegistrationPassword2);

        JButton acceptRegisterButton = new JButton("Register");
        acceptRegisterButton.setBounds(20, 130, 100, 25);
        acceptRegisterButton.setLocation(registerFrame.getWidth() / 2 - acceptRegisterButton.getWidth() / 2 - 5, registerFrame.getY() + registerFrame.getY() / 2 - 50);
        acceptRegisterButton.addActionListener(new register());


        registerFrame.add(acceptRegisterButton);
        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if( userRegistrationSurname.getText().equals("") || userRegistrationLastName.getText().equals("") ||
                    userRegistrationAddress.getText().equals("") || userRegistrationStreet.getText().equals("") ||
                    userRegistrationPLZ.getText().equals("") || userRegistrationCity.getText().equals("")) {
                JOptionPane.showMessageDialog(login.loginFrame, "Every field has to be filled out!");
            } else {
                BufferedWriter bw = new BufferedWriter(new FileWriter("userLoginData"));
                final String userSurnameWrite = userRegistrationSurname.getText();
                bw.write(userSurnameWrite + "\n");
                final String lastNameWrite = userRegistrationLastName.getText();
                bw.write(lastNameWrite + "\n");
                final String emailAddressWrite = userRegistrationAddress.getText();
                bw.write(emailAddressWrite + "\n");
                final String userStreetWrite = userRegistrationStreet.getText();
                bw.write(userStreetWrite + "\n");
                final String userPLZWrite = userRegistrationPLZ.getText();
                bw.write(userPLZWrite + "\n");
                final String userCityWrite = userRegistrationCity.getText();
                bw.write(userCityWrite + "\n");
                /*final char[] userPasswordWrite = userRegistrationPassword1.getPassword();
                bw.write(String.valueOf(userPasswordWrite) + "\n");*/
                bw.close();
                JOptionPane.showMessageDialog(registerFrame, "Registration successful!\nYou are now registered");
                registerFrame.dispatchEvent(new WindowEvent(registerFrame, WindowEvent.WINDOW_CLOSING));

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}