package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Creates an instance of the frame with the GUI before logging in.
public class PreLoginFrame extends FrameTemplate implements ActionListener {

    JButton loginButton;
    JButton signUpButton;
    JButton loadButton;
    ImageIcon calendar;

    // EFFECTS: creates an instance of a PreLoginFrame with necessary GUI elements
    public PreLoginFrame(CourseAppGUI c) {
        super("Welcome!", c);
        this.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel();
        title.setText("Hi! Welcome to Schedules");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        loginButton = new JButton("Log In");
        loginButton.addActionListener(this);
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);

        loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);

        // Reference: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        calendar = new ImageIcon(new ImageIcon("data/calendar.png").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT));

        title.setIcon(calendar);

        this.add(title);
        this.add(loginButton);
        this.add(signUpButton);
        this.add(loadButton);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: listens to events from buttons.
    //          If loginButton is pressed, opens LoginFrame
    //          If signUpButton is pressed, opens SignUpFrame
    //          If loadButton is pressed, attempts to load data from a previous session.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Opening Login!");
            new LoginFrame(courseApp);
            this.dispose();
        } else if (e.getSource() == signUpButton) {
            System.out.println("Opening Signup!");
            new SignupFrame(courseApp);
            this.dispose();
        } else if (e.getSource() == loadButton) {
            System.out.println("Loading Data!");
            courseApp.loadSession();
            JOptionPane.showMessageDialog(this,
                    "Data Loaded!",
                    "Load Session", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}