package ui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends FrameTemplate implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {
        super("Log in");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setText("Login:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        loginButton = new JButton("Log In");
        loginButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Logged In!");
            new HomeFrame();
            this.dispose();
        }
    }
}