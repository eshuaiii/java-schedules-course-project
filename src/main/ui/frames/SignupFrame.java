package ui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupFrame extends FrameTemplate implements ActionListener {

    JTextField usernameField;
    JTextField firstNameField;
    JTextField lastNameField;
    JPasswordField passwordField;
    JButton signupButton;

    public SignupFrame() {
        super("Sign up");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(10, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setText("Signup:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        passwordField = new JPasswordField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name:");
        firstNameLabel.setVerticalAlignment(JLabel.CENTER);
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name:");
        lastNameLabel.setVerticalAlignment(JLabel.CENTER);
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signupButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            System.out.println("Signed Up!");
            new HomeFrame();
            this.dispose();
        }
    }
}