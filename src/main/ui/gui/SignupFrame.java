package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates an instance of the frame with the signup GUI.
public class SignupFrame extends FrameTemplate implements ActionListener {

    JLabel title;
    JLabel usernameLabel;
    JLabel firstNameLabel;
    JLabel lastNameLabel;
    JLabel passwordLabel;
    JTextField usernameField;
    JTextField firstNameField;
    JTextField lastNameField;
    JPasswordField passwordField;
    JButton signupButton;
    JButton goBackButton;

    // EFFECTS: creates a new SignupFrame instance with necessary GUI elements
    public SignupFrame(CourseAppGUI c) {
        super("Sign up", c);
        this.setLayout(new GridLayout(11, 1, 10, 10));

        usernameField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        passwordField = new JPasswordField();

        createLabels();

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(this);

        addElements();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the labels for the GUI.
    private void createLabels() {
        title = new JLabel();
        title.setText("Signup:");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);

        firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name:");
        firstNameLabel.setVerticalAlignment(JLabel.CENTER);
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name:");
        lastNameLabel.setVerticalAlignment(JLabel.CENTER);
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds elements to the JFrame
    private void addElements() {
        this.add(title);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(signupButton);
        this.add(goBackButton);
    }

    // MODIFIES: this
    // EFFECTS: listens to button press events
    //          If signupButton is pressed, checks if all inputs are valid before attempting to sign up user
    //          If goBackButton is pressed, goes back to PreLoginFrame
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            if (usernameField.getText().isEmpty() || firstNameField.getText().isEmpty()
                    || lastNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty inputs, please!",
                        "Empty inputs!", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    String password = String.valueOf(passwordField.getPassword());
                    boolean result = courseApp.signup(usernameField.getText(), firstNameField.getText(),
                            lastNameField.getText(), password);
                    if (result) {
                        new HomeFrame(courseApp);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Oops, the account already exists. Try signing in instead?",
                                "Account Exists", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(this, "No empty inputs, please!","Empty inputs!",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }  else if (e.getSource() == goBackButton) {
            new PreLoginFrame(courseApp);
            this.dispose();
        }
    }
}