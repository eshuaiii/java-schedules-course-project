package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates an instance of the frame with the Login GUI.
public class LoginFrame extends FrameTemplate implements ActionListener {

    JLabel title;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton goBackButton;

    // EFFECTS: creates a new LoginFrame with all required elements.
    public LoginFrame(CourseAppGUI c) {
        super("Log in", c);
        this.setLayout(new GridLayout(7, 1, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        createLabels();

        loginButton = new JButton("Log In");
        loginButton.addActionListener(this);
        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(this);

        this.add(title);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(goBackButton);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the labels corresponding to login
    private void createLabels() {
        title = new JLabel();
        title.setText("Login:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: listens to events from buttons
    //          If loginButton, checks if the fields are empty before attempting to log in.
    //          If goBackButton, returns to PreLoginFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (usernameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty inputs, please!","Empty inputs!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    String password = String.valueOf(passwordField.getPassword());
                    if (courseApp.login(usernameField.getText(), password)) {
                        new HomeFrame(courseApp);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,"Oops, those don't look right. Try again!",
                                "Incorrect Details", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(this,"No empty inputs, please!","Empty inputs!",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (e.getSource() == goBackButton) {
            new PreLoginFrame(courseApp);
            this.dispose();
        }
    }
}